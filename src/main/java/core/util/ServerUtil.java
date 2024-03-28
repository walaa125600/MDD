package core.util;

import com.jcraft.jsch.*;
import core.constants.mdd.GeneralConstants;

import java.io.*;
import java.util.Properties;

public class ServerUtil {

    //Initialize instances of properties files to be used in all tests
    static Properties swfGeneralCofigsProps = Property.fromFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME);
    static Properties saaGeneralCofigsProps = Property.fromFile(core.constants.saa.GeneralConstants.GENERAL_CONFIG_FILE_NAME);

    public static String transferFileToServer(String filePath, String destinationPath, String app) throws JSchException, SftpException, IOException {

        Log.info("Initializing connection to remote server");
        //Create session to the application's remote server
        Session session = setupSshSession(app);
        session.connect();
        if (session.isConnected()) {
            Log.info("Connection to SFTP server is successfully");
        } else {
            Log.info("Connection to SFTP server not connected");
            return GeneralConstants.FAILED;
        }

        //Open sftp channel to server to securely transfer files. MAKE SURE THAT SFTP SERVICE IS UP AND RUNNING ON SERVER
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.setInputStream(null);
        InputStream output = sftpChannel.getInputStream();
        sftpChannel.connect();
        Log.info("uploading file to server");
        try {
            sftpChannel.put(filePath, destinationPath);
            Log.info("File uploaded");
        } catch (Exception e) {
            Log.info(e.toString());
            return GeneralConstants.FAILED;
        }
        closeConnection(sftpChannel, session);
        return GeneralConstants.SUCCESS;
    }

    public static Session setupSshSession(String application) throws JSchException {
        JSch jsch = new JSch();
        String rmUsername = "";
        String rmHost = "";
        String rmPassword = "";
        String rmPort = "";
        //Get server credentials from config file based on required application
        switch (application) {
            case (GeneralConstants.MDD_APP_NAME):
                rmUsername = swfGeneralCofigsProps.getProperty(GeneralConstants.REMOTE_SERVER_USERNAME);
                rmHost = swfGeneralCofigsProps.getProperty(GeneralConstants.REMOTE_SERVER_IP);
                rmPassword = swfGeneralCofigsProps.getProperty(GeneralConstants.REMOTE_SERVER_PASSWORD);
                rmPort = swfGeneralCofigsProps.getProperty(GeneralConstants.REMOTE_SERVER_PORT);
                break;
            case (GeneralConstants.SAA_APP_NAME):
                rmUsername = saaGeneralCofigsProps.getProperty(core.constants.saa.GeneralConstants.REMOTE_SERVER_USERNAME);
                rmHost = saaGeneralCofigsProps.getProperty(core.constants.saa.GeneralConstants.REMOTE_SERVER_IP);
                rmPassword = saaGeneralCofigsProps.getProperty(core.constants.saa.GeneralConstants.REMOTE_SERVER_PASSWRD);
                rmPort = saaGeneralCofigsProps.getProperty(core.constants.saa.GeneralConstants.REMOTE_SERVER_PORT);
                break;
        }


        Session session = jsch.getSession(rmUsername, rmHost, Integer.parseInt(rmPort));
        session.setPassword(rmPassword);
        Properties config = new Properties();
        config.put("PreferredAuthentications", "publickey,keyboard-interactive,password");
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);
        return session;
    }

    public static String runCommand(String command, String app) throws JSchException, IOException, InterruptedException {
        Log.info("Run command = " + command);
        Session session = setupSshSession(app);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.setErrStream(System.err);
        channel.setInputStream(null);
        Log.info("Connect to session...");
        InputStream output = channel.getInputStream();
        channel.connect();
        Log.info("Session Connected.");
        byte[] tmp = new byte[1024];
        Thread.sleep(3000);
        String result = null;
        while (output.available() > 0) {
            int i = output.read(tmp, 0, 1024);
            if (i < 0) {
                break;
            }
            result = result + new String(tmp, 0, i);
            Thread.sleep(3000);
        }
        closeConnection(channel, session);
        Log.info("Response from command = " + result);
        return result;

    }

    public static String runCommand2(String command, ChannelExec channel) throws JSchException, IOException, InterruptedException {
        Log.info("Run command = " + command);
        channel.setCommand(command);
        channel.setErrStream(System.err);
        channel.setInputStream(null);
        channel.connect();
        InputStream output = channel.getInputStream();
        byte[] tmp = new byte[1024];
        Thread.sleep(10000);
        String result = null;
        while (output.available() > 0) {
            int i = output.read(tmp, 0, 1024);
            if (i < 0) {
                break;
            }
            result = result + new String(tmp, 0, i);
            Thread.sleep(3000);
        }
        Log.info("Response from command = " + result);
        return result;
    }

    public static ChannelExec openChannel(Session session) throws JSchException {
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        return channel;
    }

    public static String moveFileInServer(String from, String to, String app) throws JSchException, SftpException {

        Log.info("Initializing remote connection to server...");
        //Create session to the application's remote server
        Session session = setupSshSession(app);
        session.connect();
        if (session.isConnected()) {
            Log.info("Connection to SFTP server is successfully");
        } else {
            Log.info("Connection to SFTP server not connected");
            return GeneralConstants.FAILED;
        }

        //Open sftp channel to server to securely transfer files. MAKE SURE THAT SFTP SERVICE IS UP AND RUNNING ON SERVER
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        sftpChannel.rename(from, to);
        closeConnection(sftpChannel, session);
        return GeneralConstants.SUCCESS;
    }

    private static void closeConnection(ChannelSftp channel, Session session) {
        try {
            Log.info("Closing Sftp connection");
            channel.quit();
        } catch (Exception e) {
            Log.error("Failed to close Sftp connection", e);
        }
        session.disconnect();
    }

    public static void closeConnection(ChannelExec channel, Session session) {
        try {
            channel.disconnect();
        } catch (Exception e) {
            Log.error("Failed to close Execution connection", e);
        }
        session.disconnect();
    }
}

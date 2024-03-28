package core.util;

import com.google.common.io.CharStreams;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import core.constants.mdd.GeneralConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ProcessManager extends ServerUtil{

    public static String executeCommandAndReturnPID(ProcessBuilder builder, String commandEndingString) throws Exception {

        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String pid = "";
        while (true) {
            String line = reader.readLine();
            Log.info(line);
            if (line.contains(commandEndingString)) {
                pid=Long.toString(process.pid());
                Log.info("Process Id: " + pid);
                break;
            }
        }
        return pid;
    }
    public static String runProcessAndReturnPid(String Process, String app) throws JSchException, IOException {
        Log.info("Run command = " + Process);
        Session session = setupSshSession(app);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand("powershell.exe \"Start-Process "+ Process + " -PassThru | Select-Object -ExpandProperty Id\"");
        channel.setErrStream(System.err);
        channel.setInputStream(null);
        Log.info("Connect to session...");
        channel.connect();
        InputStream output = channel.getInputStream();
        String result = CharStreams.toString(new InputStreamReader(output));
        String pid = result.replace(System.getProperty("line.separator"), "");
        closeConnection(channel, session);
        Log.info("PID = " + result);
        return pid;
    }

    public static String endProcessWithID(String pid, String app) throws JSchException, IOException {
        Log.info("Ending Process with ID = " + pid);
        Session session = setupSshSession(app);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand("powershell.exe \"taskkill /PID "+ pid + " /F\"");
        channel.setErrStream(System.err);
        channel.setInputStream(null);
        System.out.println("Connect to session...");
        channel.connect();
        InputStream output = channel.getInputStream();
        String result = CharStreams.toString(new InputStreamReader(output));
        closeConnection(channel, session);
        if(!result.isEmpty()){
            Log.info("Failed to terminate process with id: " +pid +" command output:"+ result);
            return GeneralConstants.FAILED;
        }else {
            Log.info("Process terminated successfully");
            result = GeneralConstants.SUCCESS;
        }
        return result;
    }
    public static boolean isProcessRunning(String processName, String app) throws JSchException, IOException {
        Log.info("finding Process with name = " + processName);
        Session session = setupSshSession(app);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand("powershell.exe \"get-process "+ processName + " |select -expand id \"");
        channel.setErrStream(System.err);
        channel.setInputStream(null);
        System.out.println("Connect to session...");
        channel.connect();
        InputStream output = channel.getInputStream();
        String result = CharStreams.toString(new InputStreamReader(output));
        closeConnection(channel, session);
        if(result.contains("Cannot find a process")){
            Log.info("Process is not running " +processName +" command output:"+ result);
            return false;
        }else {
            Log.info("Process is running with ID: "+ result);
            return true;
        }
    }

    public static boolean isProcessRunning(int pid, String app) throws JSchException, IOException {
        Log.info("finding Process with name = " + pid);
        Session session = setupSshSession(app);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand("powershell.exe \"Get-Process -Id "+pid+" | Select-Object -ExpandProperty ProcessName\"");
        channel.setErrStream(System.err);
        channel.setInputStream(null);
        System.out.println("Connect to session...");
        channel.connect();
        InputStream output = channel.getInputStream();
        String result = CharStreams.toString(new InputStreamReader(output));
        closeConnection(channel, session);
        if(result.contains("Cannot find a process")){
            Log.info("Process is not running " +pid +" command output:"+ result);
            return false;
        }else {
            Log.info("Process is running with Name: "+ result);
            return true;
        }
    }

    public static String getProcessIdUsingPort(String port,String channelType, String app) throws JSchException, IOException {
        Log.info("Run command = " + port);
        Session session = setupSshSession(app);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        switch (channelType){
            case "UDP":
                channel.setCommand("powershell.exe \"(Get-NetUDPEndpoint  -LocalPort "+port+").OwningProcess |Select-Object -expand id\"");
                break;
            case "TCP":
                channel.setCommand("powershell.exe \"(Get-NetTCPConnection -LocalPort "+port+").OwningProcess |Select-Object -expand id\"");
                break;
        }
        channel.setErrStream(System.err);
        channel.setInputStream(null);
        Log.info("Connect to session...");
        channel.connect();
        InputStream output = channel.getInputStream();
        String result = CharStreams.toString(new InputStreamReader(output));
        String pid = result.replace(System.getProperty("line.separator"), "");
        closeConnection(channel, session);
        Log.info("PID = " + result);
        return pid;
    }

    public static String getProcessIdUsingExecutionCommand(String command, String app) throws JSchException, IOException {
        Log.info("Run command = wmic Path win32_process where \"CommandLine like '%"+command+"-%'\" get processid");
        Session session = setupSshSession(app);
        session.connect();
        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand("wmic Path win32_process where \"CommandLine like '%"+command+"-%'\" get processid");
        channel.setErrStream(System.err);
        channel.setInputStream(null);
        Log.info("Connect to session...");
        channel.connect();
        InputStream output = channel.getInputStream();
        String result = CharStreams.toString(new InputStreamReader(output));
        String pid = result.replace(System.getProperty("line.separator"), "");
        closeConnection(channel, session);
        Log.info("PID = " + result);
        return pid;
    }


    public static void executeCommandOnLocalMachine(ProcessBuilder builder) throws Exception {
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = reader.readLine();
        while (line != null) {

            Log.info(line);
            line = reader.readLine();
        }
    }
}

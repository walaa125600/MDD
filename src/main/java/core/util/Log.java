package core.util;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import core.constants.mdd.GeneralConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class Log {

    public static ExtentTest test;

    //Initialize instances of properties files to be used in all tests
    static Properties generalConfigsProps = Property.fromFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME);
    static boolean  addLogToExtentReportFlag = (generalConfigsProps.getProperty(GeneralConstants.ADD_LOG_TO_EXTENT_REPORT).equalsIgnoreCase(GeneralConstants.TRUE));



    // Initialize Log4j logs

    private static final Logger LOG = LogManager.getLogger(Log.class.getName());//

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

    public static void startTestCase(String sTestCaseName){

        if(test != null && addLogToExtentReportFlag)
            test.log(Status.INFO, sTestCaseName + " Test is CREATED");


        LOG.info("****************************************************************************************");

        LOG.info("****************************************************************************************");

        LOG.info("$$$$$$$$$$$$$$$$$$$$$           "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

        LOG.info("****************************************************************************************");

        LOG.info("****************************************************************************************");

    }

    //This is to print log for the ending of the test case

    public static void endTestCase(String sTestCaseName){

        if(test != null && addLogToExtentReportFlag)
            test.log(Status.INFO, sTestCaseName + " Test has ENDED");

        LOG.info("XXXXXXXXXXXXXXXXXXXXXXX      "+"      -E---N---D-     "+ sTestCaseName+ "             XXXXXXXXXXXXXXXXXXXXXX");

        LOG.info("X");

        LOG.info("X");

        LOG.info("X");

        LOG.info("X");

    }

    // Need to create these methods, so that they can be called

    private static void checkForExtentTest(String massage , Status status)
    {
        if(test != null && addLogToExtentReportFlag)
            test.log(status, massage);
    }

    public static void info(String message) {
        LOG.info(message);
        checkForExtentTest(message , Status.INFO);
    }

    public static void warn(String message, Exception e) {
        LOG.warn(message, e);
        checkForExtentTest(message , Status.WARNING);
    }

    public static void error(String message, Exception e) {
        LOG.error(message, e);
        checkForExtentTest(message , Status.ERROR);
        checkForExtentTest(e.getMessage() ,Status.ERROR);
    }

    public static void fatal(String message) {
        LOG.fatal(message);
        checkForExtentTest(message,Status.FATAL);
    }

    public static void debug(String message) {
        LOG.debug(message);
        checkForExtentTest(message ,Status.DEBUG);
    }


}

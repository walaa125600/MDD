package core.constants.mdd;


public class GeneralConstants {


    //  **********************   Test Data config file and its properties key names ***************************

    //Test data configs file and its properties key names
    public static final String TEST_DATA_CONFIG_FILE_NAME = System.getProperty("user.dir") + "/src/test/resources/configFiles/mdd/TestDataConfig.properties";
    public static final String TEST_CONFIG_FILE_NAME = System.getProperty("user.dir") + "/src/test/resources/configFiles/mdd/GeneralConfigs.properties";

    // Test data strategy to get test data source type and implementing classes


    //  **********************   General config file and its properties key names ***************************
    public static final String GENERAL_CONFIG_FILE_NAME = System.getProperty("user.dir") + "/src/test/resources/configFiles/mdd/GeneralConfigs.properties";

    public static final String DEFAULT_DOWNLOAD_PATH = "defaultDownloadPath";
    public static final String DEFAULT_UPLOAD_PATH = "defaultUploadPath";


    // Extent report configs
    public static final String SCREENSHOT_FAILD_TESTS_PATH = "screenshotsOfFailedTestsPath";
    public static final String EXTENT_REPORT_FILE_PATH = "extentReportFilepath";
    public static final String EXTENT_REPORT_TITLE = "extentReportTitle";
    public static final String EXTENT_REPORT_NAME = "extentReportName";
    public static final String ADD_LOG_TO_EXTENT_REPORT = "addLogToExtentReport";


    // *****************     Database config file and its properties key names     **************************
    public static final String DB_CONFIG_FILE_NAME = System.getProperty("user.dir") + "/src/test/resources/configFiles/screening/DBConfigs.properties";

    //Different DB configs


    // Login Credentials
    public static final String MDD_LOGIN_URL = "mdd.login.url";


    // General constants
    public static final String SUCCESS = "Success";
    public static final String FAILED = "Failed";

    public static final String ADD = "Add";
    public static final String REMOVE = "remove";
    public static final String ADD_ALL = "Add All";
    public static final String REMOVE_ALL = "Remove All";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String SAVE = "Save";
    public static final String STRING_DELIMETER = "#";
    public static final String FILE_DELIMETER = "/";
    public static final String MISMATCH_ERR_MSG = " Value in DB Mismatched the VALUE in page";
    public static final String POM_EXCEPTION_ERR_MSG = "Test Failed due to an exception occurred in POM's method";
    public static final String ACTUAL_EXPECTED_ERR_MSG = "Test Failed due to an actual and expected results mismatched";
    public static final String DB_ERROR_MSG = "No results found in DB or DB error occurred";


    // Deployment server's configs to get server
    public static final String REMOTE_SERVER_IP = "remoteServerIp";
    public static final String REMOTE_SERVER_USERNAME = "remoteServerUserName";
    public static final String REMOTE_SERVER_PASSWORD = "remoteServerPassword";
    public static final String REMOTE_SERVER_PORT = "remoteServerPort";

    public static final String UPLOAD_FILE_PATH = "/src/test/resources/uploadsAndDownloads/uploads/";

    public static final String MDD_APP_NAME = "MDD";
    public static final String SAA_APP_NAME = "SAA";

}
  

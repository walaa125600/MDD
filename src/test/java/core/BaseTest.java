package core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import core.constants.mdd.GeneralConstants;
import core.util.Log;
import core.util.Property;
import core.util.Randomizer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest {

    //Selenium and Angular web-drivers
    public WebDriver driver;
    JavascriptExecutor jsDriver;

    //Extent report objects
    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public ExtentTest test;

    //Initialize instances of properties files to be used in all tests

    public static String userDirectoryPath = System.getProperty("user.dir");
    public static Properties generalConfigsProps = Property.fromFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME);
    public static Properties testDataConfigsProps = Property.fromFile(GeneralConstants.TEST_DATA_CONFIG_FILE_NAME);

    // Browser's default download path config from properties file
    public static String browserDefaultDownloadPath = System.getProperty("user.dir") + generalConfigsProps.getProperty(GeneralConstants.DEFAULT_DOWNLOAD_PATH);
    public static String browserDefaultUploadPath = System.getProperty("user.dir") + generalConfigsProps.getProperty(GeneralConstants.DEFAULT_UPLOAD_PATH);

    @BeforeSuite(description = "Setting up extent report", alwaysRun = true)
    @Parameters("browserType")
    public void setExtent(String browserType) {
        try {
            Log.info("Setting up extent report before test on browser: " + browserType);
            // get report file path
            String extentReportFilePath = generalConfigsProps.getProperty(GeneralConstants.EXTENT_REPORT_FILE_PATH);
            // specify location of the report
            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + extentReportFilePath + Randomizer.dateTime + ".html");

            htmlReporter.config().setDocumentTitle(generalConfigsProps.getProperty(GeneralConstants.EXTENT_REPORT_TITLE)); // Tile of report
            htmlReporter.config().setReportName(generalConfigsProps.getProperty(GeneralConstants.EXTENT_REPORT_NAME)); // Name of the report
            htmlReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            // Passing General information
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", browserType);


        } catch (Exception e) {
            Log.error("Error occurred while setting up extent reports on " + browserType, e);
        }
    }

    @BeforeSuite(description = "Deleting all previously downloaded files before running suite", alwaysRun = false)
    public void clearDownloadedFiles() {
        try {
            FileUtils.cleanDirectory(new File(browserDefaultDownloadPath));
        } catch (IOException e) {
            Log.error("Error occurred while deleting download files before test " + new Object() {
            }
                    .getClass()
                    .getName() + "." + new Object() {
            }
                    .getClass()
                    .getEnclosingMethod()
                    .getName(), e);
        }
    }

    @Parameters({"browserType"})
    @BeforeSuite(description = "Setting up selenium web driver before each class run", alwaysRun = true)
    public void loadConfiguration(String browserType) {
        try {
            Log.info("Initialize Selenium web driver before tests' Class");
            // initialize selenium driver that is set as a config in regressionTestng.xml
            switch (browserType) {
                case ("Chrome"):

                    //   WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(setChromeOption());
                    break;
                case ("Firefox"):
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(setFireFoxOption());
                    break;
                case ("IE"):
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case ("Edge"):
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(setEdgeOptions());
                    break;
                case ("Opera"):
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
            }

            jsDriver = (JavascriptExecutor) driver;
            driver.manage().window().maximize();

            driver.get(generalConfigsProps.getProperty(GeneralConstants.MDD_LOGIN_URL));
            //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Log.info("Selenium webDriver was initialized successfully");
        } catch (Exception e) {
            Log.error("Error occurred while initializing selenium web driver", e);
            Assert.fail("Error occurred while initializing selenium web driver");
        }

    }

    private static ChromeOptions setChromeOption() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> ChromePrefs = new HashMap<>();
        ChromePrefs.put("profile.default.content_settings.popups", 0);
        ChromePrefs.put("download.default_directory", browserDefaultDownloadPath);
        options.addArguments("force-device-scale-factor=1");
        options.addArguments("high-dpi-support=1");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir"));
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("prefs", ChromePrefs);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        return options;
    }

    private static FirefoxOptions setFireFoxOption() {
        // Creating firefox profile
        FirefoxProfile profile = new FirefoxProfile();
        // Instructing firefox to use custom download location
        profile.setPreference("browser.download.folderList", 2);
        // Setting custom download directory
        profile.setPreference("browser.download.dir", browserDefaultDownloadPath);
        // Skipping Save As dialog box for types of files with their MIME
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "text/csv,application/java-archive, application/x-msexcel,application/excel," +
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/x-excel," +
                        "application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml," +
                        "application/vnd.microsoft.portable-executable");
        profile.setPreference("layout.css.devPixelsPerPx", "0.9");
        // Creating FirefoxOptions to set profile
        FirefoxOptions option = new FirefoxOptions();
        option.setProfile(profile);
        return option;
    }

    private static EdgeOptions setEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
        edgePrefs.put("download.default_directory", browserDefaultDownloadPath);
        edgePrefs.put("nativeEvents", false);
        options.addArguments("force-device-scale-factor=0.9");
        options.addArguments("high-dpi-support=0.9");
        options.setExperimentalOption("prefs", edgePrefs);
        return options;
    }

    @AfterMethod(description = "Logging test status to log file and extent report", alwaysRun = true)
    public void logTestStatusForReport(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                Log.info("logging Testcase FAILED " + result.getName() + " in Extent Report");
                test.log(Status.FAIL, result.getName() + " HAS FAILED"); // to add name in extent report
                test.log(Status.FAIL, "EXCEPTION Thrown is " + result.getThrowable()); // to add error/exception in extent report
                // adding screenshot.
                String screenshotPath = getScreenshot(driver, result.getName());
                System.out.println("screenshotPath " + screenshotPath);
                test.addScreenCaptureFromPath(screenshotPath);
                test.log(Status.FAIL, test.addScreenCaptureFromPath(screenshotPath) + "Test Failed");

            } else if (result.getStatus() == ITestResult.SKIP) {
                Log.info("logging Testcase SKIPPED " + result.getName() + " in Extent Report");
                test.log(Status.SKIP, "Test Case SKIPPED is " + result.getName());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                Log.info("logging Testcase SUCCESS " + result.getName() + " in Extent Report");
                test.log(Status.PASS, "Test Case PASSED is " + result.getName());
            }
            // log that test case has ended
            Log.endTestCase(result.getName());
        } catch (Exception e) {
            Log.warn("Error occurred while logging testcase " + result.getName() + " result to extent report", e);
            e.printStackTrace();
        }
    }

    private static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

        Log.info("Taking Screenshot for the FAILED Testcase");

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        //get the path of failed tests screenshots
        String screenShotsPath = generalConfigsProps.getProperty(GeneralConstants.SCREENSHOT_FAILD_TESTS_PATH);

        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + screenShotsPath + Randomizer.date + GeneralConstants.FILE_DELIMETER + screenshotName + Randomizer.dateTime + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    @AfterClass(description = "Quitting selenium driver after each class run", alwaysRun = true)
    public void closeDriver() {
        Log.info("Closing selenium WebDriver after Class");
        if (driver != null)
            driver.quit();
    }

    @AfterSuite(description = "Closing extent report after running all tests", alwaysRun = true)
    public void endReport() {
        try {
            Log.info("Closing Extent report after Suite");
            if (extent != null)
                extent.flush();
        } catch (Exception e) {
            Log.error("Error occurred while sending test report to recipients " + new Object() {
            }
                    .getClass()
                    .getName() + "." + new Object() {
            }
                    .getClass()
                    .getEnclosingMethod()
                    .getName(), e);
        }

    }


}

package eastnets.mdd.control;

import core.util.Log;

import eastnets.mdd.gui.configurationManager.ConfigurationManager;
import org.openqa.selenium.WebDriver;

public class ConfigurationManagerControl {

    public static void exportFile(WebDriver driver,String fileType) throws Exception {
        Log.info(String.format("exporting configuration file as %s", fileType));
        switch (fileType) {
            case "xml":
                ConfigurationManager.clickExportXml(driver);
                break;
            case "pdf":
                ConfigurationManager.clickExportPdf(driver);
                break;
            case "excel":
                ConfigurationManager.clickExportExcel(driver);
                break;
            default:
                throw new Exception("file type not supported");
        }

    }
    public static void downloadXml(WebDriver driver) throws Exception {
        Log.info("downloading configuration as xml");
        ConfigurationManager.clickDownload(driver);
    }
    public static void addConfiguration(WebDriver driver) throws Exception {
        Log.info("adding configuration");
        ConfigurationManager.clickAddConfiguration(driver);
    }
    public static void approveConfiguration(WebDriver driver) throws Exception {
        selectFirstConfiguration(driver);
        Log.info("approving configuration");
        ConfigurationManager.clickApproveConfiguration(driver);
    }
    public static void enableConfiguration(WebDriver driver) throws Exception {
        selectFirstConfiguration(driver);
        Log.info("enabling configuration");
        ConfigurationManager.clickEnableConfiguration(driver);
    }
    public static void editFirstConfiguration(WebDriver driver) throws Exception {
        selectFirstConfiguration(driver);
        Log.info("editing first configuration");
        ConfigurationManager.editFirstConfiguration(driver);
    }
    public static void deleteFirstConfiguration(WebDriver driver) throws Exception {
        selectFirstConfiguration(driver);
        Log.info("deleting first configuration");
        ConfigurationManager.deleteFirstConfiguration(driver);
    }
    public static void selectFirstConfiguration(WebDriver driver) throws Exception {
        Log.info("selecting first configuration");
        ConfigurationManager.clickOnFirstConfigInList(driver);
    }

}

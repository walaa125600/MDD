package eastnets.mdd.control;

import core.util.Log;
import eastnets.mdd.gui.adminstrator.settings.SettingsManager;
import org.openqa.selenium.WebDriver;

public class AdminSettingsControl {

    public static String uploadXsd(WebDriver driver, String fileSource) throws Exception {
        Log.info("upload XSD file");
        SettingsManager.clickUploadXsdTab(driver);
        SettingsManager.addXsdFile(driver, fileSource);
        return SettingsManager.clickUploadXsdButton(driver);
    }

    public static String filterWithCriteria(WebDriver driver, String criteria,String filtrationValue) throws Exception {
        String searchResult = "";
        Log.info("filter with: " + criteria + " from List");
        switch (criteria) {
            case "Type":
                searchResult = SettingsManager.filterWithType(driver, filtrationValue);
                break;
            case "Message Type":
                searchResult = SettingsManager.filterWithMessageType(driver, filtrationValue);
                break;
            case "Version":
                searchResult = SettingsManager.filterWithVersion(driver, filtrationValue);
                break;
            case "Service Name":
                searchResult = SettingsManager.filterWithServiceName(driver, filtrationValue);
                break;
            case "Usage":
                searchResult = SettingsManager.filterWithUsage(driver, filtrationValue);
                break;
            case "Status":
                searchResult = SettingsManager.filterWithStatus(driver, filtrationValue);
                break;
            default:
                Log.info("No criteria found");
        }
        return searchResult;
    }

    public void clickModifyXsd(WebDriver driver) throws Exception {
        Log.info("modify XSD");
        SettingsManager.clickModifyButton(driver);
    }
}


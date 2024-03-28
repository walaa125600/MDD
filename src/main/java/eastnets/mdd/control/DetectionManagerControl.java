package eastnets.mdd.control;

import core.constants.mdd.GeneralConstants;
import core.gui.Controls;
import core.util.Log;
import eastnets.common.gui.Navigation;
import eastnets.mdd.gui.detectionManager.DetectionEditor;
import eastnets.mdd.gui.detectionManager.DuplicationManager;


import org.openqa.selenium.WebDriver;

public class DetectionManagerControl {

    public static void prepareForFiltration(WebDriver driver) throws Exception {
        Log.info("open Filtration tab");
        DuplicationManager.openFiltrationTab(driver);
        DuplicationManager.clickClearButton(driver);
    }

   public static void selectSource(WebDriver driver, String source) throws Exception {
         Log.info("select source: " + source + " from sources list");
         DuplicationManager.selectSource(driver, source);
         DuplicationManager.moveSourceToSelectedSources(driver);
   }
    public static void insertReference(WebDriver driver, String reference) throws Exception {
        Log.info("insert reference: "+ reference );
        DuplicationManager.insertReference(driver, reference);
    }

    public static void clickSearchButton(WebDriver driver) throws Exception {
        Log.info("click search button");
        DuplicationManager.clickSearchButton(driver);
    }
    public static void selectStatus(WebDriver driver, String status) throws Exception {
        Log.info("filter with status: " + status + " from status list");
        DuplicationManager.selectStatus(driver, status);
    }
    public static String readDetectionStatusInTable(WebDriver driver) throws InterruptedException {
        String detectionStatus = DuplicationManager.readDetectionStatusInTable(driver);
        Log.info("Detection status in table: "+ detectionStatus);
        return detectionStatus;
    }
    public static void clickFirstDetectionId(WebDriver driver) throws Exception {
        Log.info("open detection");
        DuplicationManager.clickOnFirstDetectedMessage(driver);
    }
}

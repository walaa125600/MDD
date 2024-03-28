package eastnets.mdd.gui.detectionManager;

import core.gui.Controls;
import core.util.Log;
import core.util.Wait;
import eastnets.common.control.CommonAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetectionEditor {

    private static final By DETECTION_STATUS_LOCATOR = By.xpath("//h6[normalize-space()='MDD Status']/../input");
    private static final By ACCEPT_DETECTION_BTN = By.xpath("//button[@label='Accept']");
    private static final By DIALOG_ACCEPT_BTN = By.xpath("//span[@class='p-button-label'][normalize-space()='Accept']/..");


    public static void clickAccept(WebDriver driver)
    {
        Controls.performClickByJS(driver , ACCEPT_DETECTION_BTN);
    }
    public static void clickDialogAccept(WebDriver driver)
    {
        String fullElementPath = "//button[@class='"+Controls.returnFullClassnameWithChildElementAndPartialClassname(driver,DIALOG_ACCEPT_BTN,"p-confirm-dialog-accept")+"']//span[@class='p-button-label'][normalize-space()='Accept']";
        Controls.performClick(driver , By.xpath(fullElementPath));
    }

    public static String getDetectionStatus(WebDriver driver){
        return Controls.getAttributeValue(driver , DETECTION_STATUS_LOCATOR,"placeholder");
    }

    public static String getAlertMessage(WebDriver driver) throws Exception {
        return CommonAction.getAlertMassageStringWithoutWaitForAjaxLoader(driver);
    }



}

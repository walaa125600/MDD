package eastnets.mdd.gui.adminstrator.settings;

import core.gui.Controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SettingsManager {

    private static final By UPLOAD_XSD_TAB = By.xpath("//*[contains(text(),'Upload XSD')]");
    private static final By ADD_BTN = By.xpath("//div[@class='p-fileupload-buttonbar']//input");
    private static final By UPLOAD_XSD_BTN = By.xpath("//button//span[contains(text(),'Upload XSD')]");

    private static final By ALL_FILTRATION_CRITERIA = By.xpath("//p-columnfilterformelement//input[@type='text']");
    private static final By MODIFY_BTN = By.xpath("//p-button[@title='Edit']//button");



    public static void clickUploadXsdTab(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , UPLOAD_XSD_TAB);
        Controls.waitForPageToLoad(driver);
    }

    public static void addXsdFile(WebDriver driver, String fileSource) throws Exception {
        driver.findElement(ADD_BTN).sendKeys(fileSource);
        Controls.waitForPageToLoad(driver);
    }
    public static String clickUploadXsdButton(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , UPLOAD_XSD_BTN);
        Controls.waitForPageToLoad(driver);
        return Controls.checkIfAlertExist(driver);
    }
    public static void clickModifyButton(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , MODIFY_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static String filterWithType(WebDriver driver, String textToSet) {
        Controls.setTextBoxValue(driver, ALL_FILTRATION_CRITERIA, textToSet, 0);
        clickReturn(driver);
        return Controls.getElementText(driver,ALL_FILTRATION_CRITERIA,0);
    }
    public static String filterWithMessageType(WebDriver driver, String textToSet) {
        Controls.setTextBoxValue(driver, ALL_FILTRATION_CRITERIA,textToSet,1);
        clickReturn(driver);
        return Controls.getElementText(driver,ALL_FILTRATION_CRITERIA,1);
    }
    public static String filterWithVersion(WebDriver driver, String textToSet)  {
        Controls.setTextBoxValue(driver, ALL_FILTRATION_CRITERIA,textToSet,2);
        clickReturn(driver);
        return Controls.getElementText(driver,ALL_FILTRATION_CRITERIA,2);
    }
    public static String filterWithServiceName(WebDriver driver, String textToSet)  {
        Controls.setTextBoxValue(driver, ALL_FILTRATION_CRITERIA,textToSet,3);
        clickReturn(driver);
        return Controls.getElementText(driver,ALL_FILTRATION_CRITERIA,3);
    }
    public static String filterWithUsage(WebDriver driver, String textToSet)  {
        Controls.setTextBoxValue(driver, ALL_FILTRATION_CRITERIA,textToSet,4);
        clickReturn(driver);
        return Controls.getElementText(driver,ALL_FILTRATION_CRITERIA,4);
    }
    public static String filterWithStatus(WebDriver driver, String textToSet) {
        Controls.setTextBoxValue(driver, ALL_FILTRATION_CRITERIA,textToSet,5);
        clickReturn(driver);
        return Controls.getElementText(driver,ALL_FILTRATION_CRITERIA,5);
    }
    public static void clickReturn(WebDriver driver) {

        Controls.clickKeyboardReturn(driver);
    }

}

package eastnets.mdd.gui.detectionManager;

import core.gui.Controls;
import core.util.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DuplicationManager {

    private static final By SEARCH_BUTTON_LOCATOR = By.xpath("//button[@label='Search']");
    private static final By RESET_BUTTON_LOCATOR = By.xpath("//button[@label='Clear']");
    private static final By FILTER_TAB_BTN = By.xpath("//span[contains(text(),'Filter')]");
    private static final By MOVE_TO_SELECTED_SOURCES_BTN = By.xpath("//span[@class='p-button-icon pi pi-angle-right']");
    private static final By SOURCES_LIST = By.xpath("//div[@class='p-picklist-list-wrapper p-picklist-source-wrapper']//li[@role='option']");
    private static final By STATUS_LIST = By.xpath("//*[contains(text(),'Status')]/..//span");
    private static final By STATUS_IN_TABLE = By.xpath("//tbody//td[12]");
    private static final By REFERENCE_INPUT_TXT = By.xpath("//label[contains(text(),'Reference:')]/..//input[@type='text']");
/*

    private static final By DETECTION_STATUS_LOCATOR = By.xpath("//*[@class='third-column stick-column']");
    private static final By DETECTION_CALENDER_IMAGE = By.xpath("//i[@class='fa fa-calendar']");
    private static final By DETECTION_CALENDER_TODAY_BTN = By.xpath("//a[contains(text(),'Today')]");
    private static final By DETECTION_CALENDER_CLOSE_BTN = By.xpath("//button[@id='auditManagerForm:homepage_business:detectionSearch:col1:0:detectionSearchFields:dateRangeComp:closebtn']");
    private static final By FIRST_LISTED_DETECTION_ID_BTN = By.xpath("//td[@role='gridcell'][2]");
    private static final By ASSIGNEE_CLASS = By.className("ui-selectonemenu-label ui-inputfield ui-corner-all");
    private static final By ASSIGN_BTN_CLASS= By.className("ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only default-btn btnAssignClass");
    private static final By DETECTION_ACTION_DIALOG_TEXT_ID= By.className("auditManagerForm:homepage_business:Detections_list:detectionActionDialog:text");
    private static final By DETECTION_ACTION_SAVE_BTN_ID= By.className("auditManagerForm:homepage_business:Detections_list:detectionActionDialog:btnNoteEditorSave");


    private static final By CURRENT_EXPANDED_MESSAGE_TEXT_ID_TXT = By.id("auditManagerForm:homepage_business:Detections_list:violation_list:view:scannedStructuredTab:Tab_scan_record:j_idt1203");
*/

    public static void clickSearchButton(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , SEARCH_BUTTON_LOCATOR);
        Controls.waitForPageToLoad(driver);

    }

    public static void clickClearButton(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , RESET_BUTTON_LOCATOR);
        Controls.waitForPageToLoad(driver);

    }
    public static void openFiltrationTab(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , FILTER_TAB_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static void selectSource(WebDriver driver, String source) throws Exception {
        Controls.selectOptionByDisplayedText(driver,SOURCES_LIST,source);
    }
    public static void moveSourceToSelectedSources(WebDriver driver)
    {
        Controls.performClickByJS(driver , MOVE_TO_SELECTED_SOURCES_BTN);
    }
    public static void insertReference(WebDriver driver, String reference) throws Exception {
        Controls.setTextBoxValue(driver,REFERENCE_INPUT_TXT,reference);
    }
    public static void selectStatus(WebDriver driver, String status) throws Exception {
        Controls.selectOptionByDisplayedText(driver,STATUS_LIST,status);
    }
    public static String readDetectionStatusInTable(WebDriver driver) throws InterruptedException {
        Controls.scrollIntoViewJS(driver,STATUS_IN_TABLE);
        return Controls.getElementText(driver,STATUS_IN_TABLE);
    }

    public static void clickOnFirstDetectedMessage(WebDriver driver) throws Exception {
        Controls.performClick(driver , STATUS_IN_TABLE);
        Controls.waitForPageToLoad(driver);
    }
}

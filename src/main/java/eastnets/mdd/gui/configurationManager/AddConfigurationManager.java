package eastnets.mdd.gui.configurationManager;

import core.gui.Controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddConfigurationManager {

    private static final By SEARCH_BUTTON_LOCATOR = By.xpath("//button[@label='Search']");
    private static final By RESET_BUTTON_LOCATOR = By.xpath("//button[@label='Clear']");
    private static final By FILTER_TAB_BTN = By.xpath("//span[contains(text(),'Filter')]");
    private static final By MOVE_TO_SELECTED_SOURCES_BTN = By.xpath("//span[@class='p-button-icon pi pi-angle-right']");
    private static final By SOURCES_LIST = By.xpath("//div[@class='p-picklist-list-wrapper p-picklist-source-wrapper']//li[@role='option']");
    private static final By STATUS_LIST = By.xpath("//*[contains(text(),'Status')]/..//span");
    private static final By STATUS_IN_TABLE = By.xpath("//tbody//td[12]");
    private static final By REFERENCE_INPUT_TXT = By.xpath("//label[contains(text(),'Reference:')]/..//input[@type='text']");

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

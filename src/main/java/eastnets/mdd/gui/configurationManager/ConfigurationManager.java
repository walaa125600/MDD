package eastnets.mdd.gui.configurationManager;

import core.gui.Controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfigurationManager {

    private static final By EXPORT_XML_BTN = By.xpath("//span[@class='pi pi-window-maximize p-button-icon ng-star-inserted']");
    private static final By DOWNLOAD_BTN = By.xpath("//span[@class='p-button-icon p-button-icon-left pi pi-download']");
    private static final By ADD_CONFIGURATION_BTN = By.xpath("//span[@class='pi pi-plus p-button-icon ng-star-inserted']");
    private static final By EXPORT_PDF_BTN = By.xpath("//span[@class='pi pi-file-pdf p-button-icon ng-star-inserted']");
    private static final By EXPORT_EXCEL = By.xpath("//span[@class='pi pi-file-excel p-button-icon ng-star-inserted']");
    private static final By SELECT_CONFIGURATION_CHECKBOX = By.xpath("//p-tablecheckbox//div[@role='checkbox']");
    private static final By EDIT_CONFIGURATION_BTN = By.xpath("//p-button[@title='Edit Configuration']/button");
    private static final By DELETE_CONFIGURATION_BTN = By.xpath("//p-button[@title='Delete Configuration']/button");
    private static final By APPROVE_CONFIGURATION_BTN = By.xpath("//p-button[@title='Approve']/button");
    private static final By ENABLE_CONFIGURATION_BTN = By.xpath("//p-button[@title='Enable']/button");


    public static void clickExportXml(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , EXPORT_XML_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickDownload(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , DOWNLOAD_BTN);
        Controls.waitForPageToLoad(driver);
    }
    public static void clickAddConfiguration(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , ADD_CONFIGURATION_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickExportExcel(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , EXPORT_EXCEL);
        Controls.waitForPageToLoad(driver);
    }
    public static void clickExportPdf(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , EXPORT_PDF_BTN);
        Controls.waitForPageToLoad(driver);
    }
    public static void clickApproveConfiguration(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , APPROVE_CONFIGURATION_BTN);
        Controls.waitForPageToLoad(driver);
    }
    public static void clickEnableConfiguration(WebDriver driver) throws Exception {
        Controls.performClickByJS(driver , ENABLE_CONFIGURATION_BTN);
        Controls.waitForPageToLoad(driver);
    }
    public static void clickOnFirstConfigInList(WebDriver driver) throws Exception {
        Controls.performClick(driver , SELECT_CONFIGURATION_CHECKBOX);
        Controls.waitForPageToLoad(driver);
    }
    public static void editFirstConfiguration(WebDriver driver) throws Exception {
        Controls.performClick(driver , EDIT_CONFIGURATION_BTN);
        Controls.waitForPageToLoad(driver);
    }
    public static void deleteFirstConfiguration(WebDriver driver) throws Exception {
        Controls.performClick(driver , DELETE_CONFIGURATION_BTN);
        Controls.waitForPageToLoad(driver);
    }
    public static void approveFirstConfiguration(WebDriver driver) throws Exception {
        Controls.performClick(driver , APPROVE_CONFIGURATION_BTN);
        Controls.waitForPageToLoad(driver);
    }
    public static void enableFirstConfiguration(WebDriver driver) throws Exception {
        Controls.performClick(driver , ENABLE_CONFIGURATION_BTN);
        Controls.waitForPageToLoad(driver);
    }
}

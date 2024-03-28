package eastnets.mdd.gui.adminstrator.profile;

import core.gui.Controls;
import core.util.Randomizer;
import eastnets.mdd.entity.Authorization;
import eastnets.mdd.entity.Roles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;


public class AddProfile {

    private static final By PROFILE_NAME_TXT = By.xpath("//label[contains(text(),'Profile Name')]/../input");
    private static final By DESCRIPTION_TXT = By.xpath("//label[contains(text(),'Description')]/../input");
    private static final By AUTHORIZE_BTN = By.xpath("//span[contains(text(),'Authorize')]/../../a");
    private static final By ROLES_BTN = By.xpath("//span[contains(text(),'Roles')]/../../a");
    private static final By OTHER_AUTHORIZE_BTN = By.xpath("//span[contains(text(),'Other')]/../../a");
    private static final By PROFILE_SAVE_BTN = By.xpath("//span[contains(text(),'Save')]/../../button");
    private static final By PROFILE_CANCEL_BTN = By.xpath("//span[contains(text(),'Cancel')]/../../button");

    private static final By CONFIGURATION_FIELDSET = By.xpath("//p-fieldset[@legend='Configuration Manager']");

    private static final By DUPLICATES_FIELDSET = By.xpath("//p-fieldset[@legend='Duplicates Manager']");

    private static final By ROLES_FIELDSET = By.xpath("//p-tabpanel[@header='Roles']");

    private static final By BIC_CODES_FIELDSET = By.xpath("//div[contains(text(),'Available Bic Codes')]/../../../../../p-picklist");
    private static final By UNITS_FIELDSET = By.xpath("//div[contains(text(),'Available Units')]/../../../../../p-picklist");
    private static final By CATEGORIES_FILEDSET = By.xpath("//div[contains(text(),'Available Categories')]/../../../../../p-picklist");

    public static void fillProfileName(WebDriver driver, String name) throws Exception {
        Controls.setTextBoxValue(driver, PROFILE_NAME_TXT, name+ Randomizer.timestamp);
        Controls.waitForPageToLoad(driver);
    }

    public static void fillDescription(WebDriver driver, String description) throws Exception {
        Controls.setTextBoxValue(driver, DESCRIPTION_TXT, description);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickAuthorizeBtn(WebDriver driver, Authorization authorization) throws Exception {
        Controls.performClick(driver, AUTHORIZE_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickRolesBtn(WebDriver driver, Roles roles) throws Exception {
        Controls.performClick(driver, ROLES_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickOtherAuthorizeBtn(WebDriver driver) throws Exception {
        Controls.performClick(driver, OTHER_AUTHORIZE_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickSaveBtn(WebDriver driver) throws Exception {
        Controls.performClick(driver, PROFILE_SAVE_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickCancelBtn(WebDriver driver) throws Exception {
        Controls.performClick(driver, PROFILE_CANCEL_BTN);
        Controls.waitForPageToLoad(driver);
    }

    public static void setConfigurationFieldset(WebDriver driver, List<String> values, String action) throws Exception {
        Controls.selectMultipleFromFieldSet(driver, CONFIGURATION_FIELDSET, values, action);
        Controls.waitForPageToLoad(driver);
    }

    public static void setDuplicatesFieldset(WebDriver driver, List<String> values, String action) throws Exception {
        Controls.selectMultipleFromFieldSet(driver, DUPLICATES_FIELDSET, values, action);
        Controls.waitForPageToLoad(driver);
    }

    public static void setRolesFieldset(WebDriver driver, List<String> values, String action) throws Exception {
        Controls.selectMultipleFromFieldSet(driver, ROLES_FIELDSET, values, action);
        Controls.waitForPageToLoad(driver);
    }

    public static void setBicCodesFieldset(WebDriver driver, List<String> values, String action) throws Exception {
        Controls.selectMultipleFromFieldSet(driver, BIC_CODES_FIELDSET, values, action);
        Controls.waitForPageToLoad(driver);
    }


    public static void setUnitsFieldset(WebDriver driver, List<String> values, String action) throws Exception {
        Controls.selectMultipleFromFieldSet(driver, UNITS_FIELDSET, values, action);
        Controls.waitForPageToLoad(driver);
    }

    public static void setCategoriesFieldset(WebDriver driver, List<String> values, String action) throws Exception {
        Controls.selectMultipleFromFieldSet(driver, CATEGORIES_FILEDSET, values, action);
        Controls.waitForPageToLoad(driver);
    }
}

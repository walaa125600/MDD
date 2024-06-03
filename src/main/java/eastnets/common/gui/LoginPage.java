package eastnets.common.gui;

import core.gui.Controls;
import core.util.Log;
import core.util.Wait;
import eastnets.common.control.CommonAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private static final By USERNAME_INPUT_TXT = By.xpath("//input[@type='text']");
    private static final By PASSWORD_INPUT_TXT = By.xpath("//input[@type='password']");
    private static final By LOGIN_BTN = By.xpath("//button[@type='button']");
    private static final By MDD_MULTIPLE_SESSION_CONTINUE_BTN = By.xpath("//span[contains(text(),'Continue Login')]");
    private static final By MDD_SESSION_REMOVED_OK_BTN = By.xpath("//button[@label='OK']");

    private static final By SAA_LOGIN_OPTION_XPATH = By.xpath("//div[@class='auth_OL']");
    private static final By SAA_USERNAME_INPUT_ID = By.id("gwt-debug-platform_login-username");
    private static final By SAA_PASSWORD_INPUT_ID = By.id("gwt-debug-platform_login-password");
    private static final By SAA_LOGIN_INSTANCE_LIST_XPATH = By.xpath("//input[@class='auth_ACC']");
    private static final By SAA_MULTIPLE_SESSION_OK_BUTTON_ID = By.id("gwt-debug-dialog-ask-0-ok");

    private static final By SAA_SWIFT_LOGO_XPATH = By.xpath("//div[@class='swp_LP']");

    private static final By SAA_LOGIN_BUTTON_ID = By.id("gwt-debug-platform_login-logon");
    private static final By USER_PROFILE_NAME=By.xpath("//ul/li[@class='topbar-item user-profile']/a");
    private static final By LOGOUT =By.xpath("//*[text()='Logout']//ancestor::li[1]");



    //MDD login method
    public static String login(WebDriver driver, String username, String password) throws Exception {

        if (Controls.checkIfElementExist(driver, MDD_SESSION_REMOVED_OK_BTN)) {
            Controls.performClick(driver, MDD_SESSION_REMOVED_OK_BTN);
        }
        if (Controls.checkIfElementExist(driver, USERNAME_INPUT_TXT)) {
            Log.info(String.format("Login with User Name = %s and Password = %s", username, password));
            Controls.clearTextBoxValue(driver, USERNAME_INPUT_TXT);
            Controls.clearTextBoxValue(driver, PASSWORD_INPUT_TXT);
            Controls.setTextBoxValue(driver, USERNAME_INPUT_TXT, username);
            Controls.setTextBoxValue(driver, PASSWORD_INPUT_TXT, password);
            Wait.time(5000);
            Controls.performClickByJS(driver, LOGIN_BTN);
            if (Controls.checkIfElementExist(driver, MDD_MULTIPLE_SESSION_CONTINUE_BTN)) {
                Controls.performClick(driver, MDD_MULTIPLE_SESSION_CONTINUE_BTN);
            }
        }
        else{
            Log.info("User Already LoggedIn");
        }
        String validationMessage=CommonAction.getAlertMassageStringWithoutWaitForAjaxLoader(driver);
        Controls.waitForPageToLoad(driver);

        return validationMessage;
    }


    public  static void Logout(WebDriver driver) throws Exception {
        Controls.waitForPageToLoad(driver);
        Controls.performClick(driver,USER_PROFILE_NAME);
        Controls.performClick(driver, LOGOUT);

    }

    // SAA login method
    public static boolean loginSAA(WebDriver driver, String username, String password, String instance) throws Exception {
        Log.info(String.format("Login with User Name = %s and Password = %s using instance = %s", username, password, instance));
        Controls.performJsClick(driver, driver.findElement(SAA_LOGIN_OPTION_XPATH));
        Controls.clearTextBoxValue(driver, SAA_USERNAME_INPUT_ID);
        Controls.setTextBoxValue(driver, SAA_USERNAME_INPUT_ID, username);
        Controls.clearTextBoxValue(driver, SAA_PASSWORD_INPUT_ID);
        Controls.setTextBoxValue(driver, SAA_PASSWORD_INPUT_ID, password);
        Controls.setOptionInDropDownTable(driver, SAA_LOGIN_INSTANCE_LIST_XPATH, instance);
        Controls.performClick(driver, SAA_LOGIN_BUTTON_ID);
        if(Controls.checkIfElementExist(driver, SAA_MULTIPLE_SESSION_OK_BUTTON_ID)){
            Controls.performClick(driver, SAA_MULTIPLE_SESSION_OK_BUTTON_ID);
        }
        Log.info("Logged in Successfully");
        // Assert login of login
        return Controls.checkIfElementExist(driver,SAA_SWIFT_LOGO_XPATH);
    }







}

package eastnets.mdd.control;

import core.gui.Controls;
import eastnets.common.control.CommonAction;
import eastnets.common.gui.Navigation;
import eastnets.mdd.entity.User;
import eastnets.mdd.gui.adminstrator.users.UserEditor;
import eastnets.mdd.gui.adminstrator.users.UsersManager;
import org.openqa.selenium.WebDriver;

public class AdminUserControl {
    public static String addUser(WebDriver driver, User user ) throws Exception {
        Navigation.ADMINISTRATION_USER.navigate(driver);
        UsersManager.clickAddUserButton(driver);
        UserEditor.selectAuthenticationMethod(driver,user.getAuthenticationMethod());
        UserEditor.fillUserName(driver,user.getUsername());
        UserEditor.fillFullUserName(driver,user.getFullUserName());
        UserEditor.fillUserEmail(driver,user.getUserEmail());
        UserEditor.selectProfile(driver, user.getSelectProfile());
        UserEditor.selectAuthenticator(driver,user.getSelectAuthenticator());
        UserEditor.fillPassword(driver,user.getPassword());
        UserEditor.fillConfirmPassword(driver,user.getConfirmPassword());
        UserEditor.clickSaveBtn(driver);
        String validationMessage = CommonAction.getAlertMassageStringWithoutWaitForAjaxLoader(driver);
       driver.navigate().refresh();
        return validationMessage;
    }

    public static boolean addUserWithAutoGenerate(WebDriver driver, User user) throws Exception {
        Navigation.ADMINISTRATION_USER.navigate(driver);
        UsersManager.clickAddUserButton(driver);
        UserEditor.selectAuthenticationMethod(driver,user.getAuthenticationMethod());
        UserEditor.fillUserName(driver,user.getUsername());
        UserEditor.fillFullUserName(driver,user.getFullUserName());
        UserEditor.fillUserEmail(driver,user.getUserEmail());
        UserEditor.selectProfile(driver, user.getSelectProfile());
        UserEditor.selectAuthenticator(driver,user.getSelectAuthenticator());
        UserEditor.setAutoGenerate(driver, user.isAutoGenerate());
        UserEditor.clickSaveBtn(driver);

        return  UserEditor.isAutoGenerateMassageExist(driver);
    }
    public static String addExistingUser(WebDriver driver, User user ) throws Exception {
        Navigation.ADMINISTRATION_USER.navigate(driver);
        UsersManager.clickAddUserButton(driver);
        UserEditor.selectAuthenticationMethod(driver,user.getAuthenticationMethod());
        UserEditor.fillUserName(driver,user.getUsername());
        UserEditor.fillFullUserName(driver,user.getFullUserName());
        UserEditor.fillUserEmail(driver,user.getUserEmail());
        UserEditor.selectProfile(driver, user.getSelectProfile());
        UserEditor.selectAuthenticator(driver,user.getSelectAuthenticator());
        UserEditor.fillPassword(driver,user.getPassword());
        UserEditor.fillConfirmPassword(driver,user.getConfirmPassword());
        UserEditor.clickSaveBtn(driver);
        String validationMessage = CommonAction.getAlertMassageStringWithoutWaitForAjaxLoader(driver);
        driver.navigate().refresh();
        return validationMessage;
    }

    public static String approveUser(WebDriver driver, String username ) throws Exception {

       // Navigation.ADMINISTRATION_USER.navigate(driver);
        UsersManager.setUserNameFilter(driver,username);
        UsersManager.clickOnLastPage(driver);
        UsersManager.clickFilter(driver);
        UsersManager.clickApproveBtn(driver);
        String validationMessage = CommonAction.getAlertMassageStringWithoutWaitForAjaxLoader(driver);
        return validationMessage;
    }
    public static String enableUser(WebDriver driver, String username ) throws Exception {
        Navigation.ADMINISTRATION_USER.navigate(driver);
        UsersManager.setUserNameFilter(driver,username);
        UsersManager.clickEnableBtn(driver);
        UsersManager.clickYesButton(driver);
        String validationMessage = CommonAction.getAlertMassageStringWithoutWaitForAjaxLoader(driver);
        return validationMessage;
    }

    public static void cancelAddingUser(WebDriver driver) throws Exception {
        Controls.waitForPageToLoad(driver);
        Navigation.ADMINISTRATION_USER.navigate(driver);
        UsersManager.clickAddUserButton(driver);
        UserEditor.clickCancelButton(driver);
    }

    public static String CheckLSAAuthorityForApproving(WebDriver driver, String username) throws Exception {
        UsersManager.searchForSpecificUserAndApproved(driver,username);
        String validationMessage = CommonAction.getAlertMassageStringWithoutWaitForAjaxLoader(driver);
        return validationMessage;
    }

    public static String CheckLSAAuthorityForDisApproving(WebDriver driver) throws Exception {
        Navigation.ADMINISTRATION_USER.navigate(driver);
        UsersManager.clickDisApproveBtn(driver);
        String validationMessage = CommonAction.getAlertMassageStringWithoutWaitForAjaxLoader(driver);
        return validationMessage;
    }
    public  static void  disapproveButtonWillSwitchTheUserToUnapproved(WebDriver driver) throws Exception {
        Navigation.ADMINISTRATION_USER.navigate(driver);
        UsersManager.checkApproveOrDisapproveStatus(driver);
    }

    public static void editInUserInformation(WebDriver driver, String username) throws Exception {
        UsersManager.searchForSpecificUserAndEdit(driver,username);

    }


}

package eastnets.mdd.gui.adminstrator.users;

import core.gui.Controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserEditor {
    private static final By Authentication_Method=By.xpath("//*[@placeholder='Select Authentication Method']");
    private static final By User_Name_Txt=By.xpath("//*[text()='User Name']//ancestor::div[1]//input");
    private static final By Full_UserName_Txt=By.xpath("//*[text()='Full User Name']//ancestor::div[1]//input");
    private static final By User_Email_Txt=By.xpath(" //*[text()='User Email']//ancestor::div[1]//input");
    private  static final By Select_Profile=By.xpath("//p-dropdown[@placeholder='Select Profile']");
    private static final By Expiration_Date=By.xpath("//input[@id='icon']");
    private static final  By Select_Authenticator= By.xpath("//span[text()='Select Authenticator']");
    private static final By PASSWORD_LOCATOR =By.xpath("(//input[@type=('password')])[1]");
    private static final By Confirm_Password_Txt=By.xpath("//*[text()='Confirm Password']//ancestor::div[1]//input");
    private static final By Save_Button=By.xpath("//button[.='Save']");
    private static final String AUTO_GENERATE_CHECKBOX= "//label[.= 'Auto-Generate password']//ancestor::div[1]//p-checkbox";

    private static final By USER_NAME_REQUIRED_MESSAGE=By.xpath("//span[text()='Username Is Required']");
    private static final By PROFILE_REQUIRED_MESSAGE=By.xpath("//span[text()='Profile Is Required']");
    private  static final By EMAIL_REQUIRED_MESSAGE=By.xpath("//span[text()='Email Is Required']");
    private  static final By PASSWORD_REQUIRED_MESSAGE=By.xpath("//span[text()='Password Is Required']");
    private static final By VALIDATION_MESSAGE_AUTO_GENERATE=By.xpath("//p[contains(.,'The Auto Generated Password Is')]");
    private static final By CANCEL_BUTTON=By.xpath("//*[text()='Cancel']");
    private static final By USER_EMAIL_EDITING=By.xpath("//label[text()='User Email']/following-sibling::input");
    public static void selectAuthenticationMethod(WebDriver driver,String authentication) throws Exception {
        Controls.selectOptionByDisplayedText(driver, Authentication_Method,authentication);
    }
    public static void fillUserName(WebDriver driver, String name)  {
        Controls.setTextBoxValue(driver, User_Name_Txt,name);
    }
    public static void fillFullUserName(WebDriver driver, String fullname)  {
        Controls.setTextBoxValue(driver,Full_UserName_Txt, fullname);
    }
    public static void fillUserEmail(WebDriver driver, String UserEmail) {
        Controls.setTextBoxValue(driver,User_Email_Txt, UserEmail);

    }
    public static void selectProfile(WebDriver driver,String profile) throws Exception {
        Controls.selectOptionByDisplayedText(driver, Select_Profile,profile);
    }
    public static void selectExpirationDate(WebDriver driver, String expirationDate)  {
        Controls.setTextBoxValue(driver,Expiration_Date, expirationDate);
    }
    public static void selectAuthenticator(WebDriver driver,String Authenticator) throws Exception {
        Controls.selectOptionByDisplayedText(driver, Select_Authenticator,Authenticator);
    }
    public static void setAutoGenerate(WebDriver driver, Boolean flag)  {
        Controls.setCheckboxValue(driver, AUTO_GENERATE_CHECKBOX , flag);

    }
    public static void fillPassword(WebDriver driver, String Password)  {
        Controls.scrollIntoViewJS(driver ,PASSWORD_LOCATOR);
        Controls.setTextBoxValue(driver, PASSWORD_LOCATOR, Password);
    }
    public static void fillConfirmPassword(WebDriver driver, String ConfirmPassword)  {
        Controls.setTextBoxValue(driver,Confirm_Password_Txt, ConfirmPassword);

    }
    public static void clickSaveBtn(WebDriver driver) throws Exception {
        Controls.performClick(driver, Save_Button);
        Controls.waitForPageToLoad(driver);
    }

    public static boolean isAutoGenerateMassageExist(WebDriver driver)
    {
        return Controls.exists(driver, VALIDATION_MESSAGE_AUTO_GENERATE);
    }

    public static void clickCancelButton(WebDriver driver) throws Exception {
        Controls.waitForPageToLoad(driver);
       Controls.performClick(driver,CANCEL_BUTTON);
        Controls.waitForPageToLoad(driver);
    }
    public static void EditUserEmail(WebDriver driver,String userEmail){
        Controls.setTextBoxValue(driver, USER_EMAIL_EDITING,userEmail);

    }








}


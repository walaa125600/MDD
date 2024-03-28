package eastnets.mdd.gui.adminstrator.profile;

import core.gui.Controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ProfileManager {

    private static final By PROFILE_ADD_BTN = By.xpath("//p-button[@title='Add Profile']//button");


    public static void clickAddProfileBtn(WebDriver driver) throws Exception {
        Controls.performClick(driver, PROFILE_ADD_BTN);
        Controls.waitForPageToLoad(driver);
    }


}

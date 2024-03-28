package eastnets.common.control;

import core.gui.Controls;
import core.constants.mdd.GeneralConstants;
import core.util.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CommonAction {
    private static final int WAIT_COEFFICIENT = 9;


    public static void startSearch(WebDriver driver, By searchButton) throws InterruptedException {
        Controls.performClickByJS(driver, searchButton);
        Wait.time(Wait.ONE_SECOND/WAIT_COEFFICIENT);
    }

    public static void resetSearch(WebDriver driver, By resetButton) throws InterruptedException {
        Controls.performClickByJS(driver, resetButton);
        driver.switchTo().alert().accept();
        Wait.time(Wait.ONE_SECOND*5);
    }

    public static boolean isUserLogged(WebDriver driver) {
        By loginButton = By.cssSelector(".fa-street-view");
        return Controls.exists(driver, loginButton);
    }

    public static void logout(WebDriver driver) {
        Controls.performClick(driver, By.xpath("//form[@id='topbar-right']/ul/li[3]/a"));
        Wait.elementVisible(driver, By.id("topbar-right:logoutDlg"), Duration.ofSeconds(5));
        Controls.performClick(driver, By.xpath("//div[@id=\"topbar-right:logoutDlg\"]/div[3]/span/button[2]"));
        Wait.elementVisible(driver, By.id("pageLoginForm"), Duration.ofSeconds(3));
    }

    public static void waitPageVisible(WebDriver driver, By validator) {
        Wait.elementVisible(driver, validator, Duration.ofSeconds(2));
    }

    private static final By ALERT_MASSAGE_LOCATOR = By.xpath("//div[@role='alert']//span[2]");

    public static String getAlertMassageString(WebDriver driver) throws InterruptedException {
        Wait.waitUntilAjaxLoaderDisappear(driver);
        Wait.time(Wait.ONE_SECOND*2);
        if (driver.findElements(ALERT_MASSAGE_LOCATOR).size() == 0)
            return null;

        return Controls.getLabelValue(driver , ALERT_MASSAGE_LOCATOR);
    }
    public static String getAlertMassageStringWithoutWaitForAjaxLoader(WebDriver driver) throws Exception {
        Wait.time(Wait.ONE_SECOND);
        if (!Controls.checkIfElementExist(driver, ALERT_MASSAGE_LOCATOR))
            return GeneralConstants.FAILED;

        return Controls.getLabelValue(driver , ALERT_MASSAGE_LOCATOR);
    }
}

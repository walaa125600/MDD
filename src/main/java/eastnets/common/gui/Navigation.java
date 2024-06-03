package eastnets.common.gui;

import core.constants.mdd.GeneralConstants;
import core.util.Wait;
import core.gui.Controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public enum  Navigation {

    DUPLICATES_MANAGER("//span[contains(text(),'Duplicates Manager')]",
            "","//span[contains(text(),'Duplicates Manager')]"),
    ADMINISTRATION_SETTINGS("//span[contains(text(),'Administration')]",
            "//span[contains(text(),'Settings')]",
            "//span[contains(text(),'Administration')]"),
    ADMINISTRATION_PROFILE("//span[contains(text(),'Administration')]",
            "//span[contains(text(),'Profile')]",
            "//span[contains(text(),'Administration')]"),
    ADMINISTRATION_USER("//span[contains(text(),'Administration')]",
                                   "//span[contains(text(),'Users')]",
                                   "//span[contains(text(),'Administration - Users')]");


    private static final By LAYOUT_MENU = By.xpath("//ul[@class='layout-menu']");
    private static final By USER_PROFILE = By.xpath("//li[@class='topbar-item user-profile']");


    private final By navigationElement;
    private final By checkLabel;
    private final By subElement;

    Navigation(String navigationElement,String subElement, String checkLabel) {
        this.navigationElement = By.xpath(navigationElement);
        this.checkLabel = By.xpath(checkLabel);
        this.subElement = By.xpath(subElement);
    }

    public String navigate(WebDriver driver) throws Exception {
        Controls.performMouseHover(driver, LAYOUT_MENU);
        Wait.waitUntilElementToBeClickable(driver, navigationElement, Wait.ONE_MINUTE_DURATION);
        Controls.scrollIntoViewJS(driver, navigationElement);
        Controls.performClickByJS(driver, navigationElement);
        if(!subElement.toString().isEmpty()
                &&driver.findElement(By.xpath(navigationElement.toString().replaceFirst("By.xpath: ","")+"/../.."))
                .getAttribute("class").contains("active")) {
            Controls.scrollIntoViewJS(driver, subElement);
            Controls.performClickByJS(driver, subElement);
        }
        Controls.performMouseHover(driver, USER_PROFILE);

        if (driver.findElements(checkLabel).size() == 0)
            return GeneralConstants.FAILED;
        Controls.waitForPageToLoad(driver);
        return GeneralConstants.SUCCESS;
    }
}


package core.gui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ComboBox {
    private final By selectBoxLocator;
    private final String itemLocator;

    public ComboBox(String selectBoxLocator) {
        this.selectBoxLocator = By.id(selectBoxLocator);
        this.itemLocator = String.format("//ul[@id=\"%s\"]", selectBoxLocator + "_items") + "/li[@data-label=\"%s\"]";
    }

    public void select(WebDriver driver, String itemName) {
        Controls.performClickByJS(driver, selectBoxLocator);
        Controls.waitAndClick(driver, By.xpath(String.format(itemLocator, itemName)), Duration.ofMillis(150));
    }
}

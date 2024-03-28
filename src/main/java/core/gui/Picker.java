package core.gui;

import core.util.Log;
import core.util.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;

public class Picker {
    private static final String FORMATTED_BUTTON_XPATH = "//div[@id=\"%s\"]/div[2]/div/button[@title=\"%s\"]";
    private static final String FORMATTED_PICKER_TABLE_XPATH = "//div[@id=\"%s\"]/div[%d]";
    private static final String FORMATTED_PICKER_TABLE_ITEM_XPATH = FORMATTED_PICKER_TABLE_XPATH + "/ul/li[@data-item-label=\"%s\"]";

    private final String divId;

    public Picker(String divId) {
        this.divId = divId;
    }

    public static By getButtonBy(String divId, Button button) {
        return By.xpath(String.format(FORMATTED_BUTTON_XPATH, divId, button.getName()));
    }

    public static By getAvailableItem(String divId, String itemName) {
        return By.xpath(String.format(FORMATTED_PICKER_TABLE_ITEM_XPATH, divId, 1, itemName));
    }

    public static By getSelectedItem(String divId, String itemName) {
        return By.xpath(String.format(FORMATTED_PICKER_TABLE_ITEM_XPATH, divId, 3, itemName));
    }

    private void waitForPicker() throws InterruptedException {
        Wait.time(Wait.ONE_SECOND/2);
    }

    public void removeAllIfEnabled(WebDriver driver) throws InterruptedException {
        Log.info("Remove all selected options if exist.");
        Controls.clickButtonIfEnabled(driver, Picker.getButtonBy(divId, Picker.Button.REMOVE_ALL));
    }

    public boolean hasAvailableItem(WebDriver driver) {
        return Controls.exists(driver, By.xpath(String.format(FORMATTED_PICKER_TABLE_XPATH + "/ul/li[1]", divId, 1)));
    }

    public void clickOnSelectedItem(WebDriver driver, String item) throws InterruptedException {
        Log.info(String.format("Click on %s",item));
        Controls.performClickByJS(driver, Picker.getSelectedItem(divId, item));
        if (!Controls.getWebElement(driver,Picker.getSelectedItem(divId, item)).getAttribute("class").contains("ui-state-highlight"))
            Controls.performClickByJS(driver, Picker.getSelectedItem(divId, item));
        waitForPicker();
    }

    public void addAvailableItem(WebDriver driver, String item) throws InterruptedException {
        Log.info(String.format("Click on the %s item",item));
        Controls.performClick(driver, Picker.getAvailableItem(divId, item));
        Log.info("Click on Add button");
        Controls.performClick(driver, Picker.getButtonBy(divId, Picker.Button.ADD));
        waitForPicker();
    }

    public void addAvailableItem(WebDriver driver, List<String> itemList) throws InterruptedException {
        for (String item : itemList) {
            Log.info(String.format("Add %s item to selected list.",item));
            addAvailableItem(driver, item);
        }
    }

    public void addAllAvailableItem(WebDriver driver) throws InterruptedException {
        Controls.waitAndClick(driver, Picker.getButtonBy(divId, Picker.Button.ADD_ALL), Duration.ofMillis(750));
        waitForPicker();
    }

    public enum Button {
        ADD("Add"),
        ADD_ALL("Add All"),
        REMOVE("Remove"),
        REMOVE_ALL("Remove All");

        private final String name;

        Button(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}

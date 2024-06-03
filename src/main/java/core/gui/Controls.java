package core.gui;

import core.constants.mdd.GeneralConstants;
import core.util.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Controls {

    public static WebElement getWebElement(WebDriver driver, By by) {
        Wait.waitForJStoLoad(driver);
        Wait.waitUntilAjaxLoaderDisappear(driver);
        // Wait.waitUntilElementToBeVisible(driver , by , Wait.ONE_MINUTE_DURATION);
        return new WebDriverWait(driver, Wait.TWO_MINUTE_DURATION).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private static Select getSelect(WebDriver driver, By by) {
        return new Select(driver.findElement(by));
    }

    public static void setTextBoxValue(WebDriver driver, By by, String textToSet) {
        if (textToSet != null && textToSet != "") {
            clearTextBoxValue(driver, by);
            getWebElement(driver, by).sendKeys(textToSet);
        }
    }

    public static void setTextBoxValue(WebDriver driver, By by, String textToSet, int elementIndex) {
        if (textToSet != null && textToSet != "") {
            List<WebElement> allElements = driver.findElements(by);
            allElements.get(elementIndex).clear();
            allElements.get(elementIndex).sendKeys(textToSet);
        }
    }

    public static void setTextBoxValueByJS(WebDriver driver, By by, String textToSet) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value = arguments[1]", getWebElement(driver, by), textToSet);
    }

    public static void clearTextBoxValue(WebDriver driver, By by) {
        getWebElement(driver, by).clear();
    }

    public static String getLabelValue(WebDriver driver, By by) {
        Wait.waitForJStoLoad(driver);
        return getWebElement(driver, by).getText();
    }

    public static void setTextBoxValue(WebDriver driver, By by, long textToSet) {
        getWebElement(driver, by).sendKeys(Long.toString(textToSet));
    }


    public static void setCheckboxValue(WebDriver driver, By alternateBy, By by, boolean toCheck) {
        WebElement checkboxInput = getWebElement(driver, by);
        boolean needToBeChanged = checkboxInput.getAttribute("class").contains("pi-check") != toCheck;
        if (needToBeChanged) {
            WebElement checkbox = getWebElement(driver, alternateBy);
            checkbox.click();
        }
    }

    public static void setCheckboxValue(WebDriver driver, String parentId, boolean toCheck) {
        setCheckboxValue(driver, By.xpath(parentId), By.xpath(parentId + "//span"), toCheck);
    }

    public static void setSelectByVisibleText(WebDriver driver, By by, String value) {
        getSelect(driver, by).selectByVisibleText(value);
    }

    public static void setSelectByTagValue(WebDriver driver, By by, String value) {
        getSelect(driver, by).selectByValue(value);
    }

    public static void waitAndClick(WebDriver driver, By by, Duration duration) {
        Wait.waitUntilElementToBeClickable(driver, by, duration);
        getWebElement(driver, by).click();
    }

    public static void performClick(WebDriver driver, By by) {
        performClick(getWebElement(driver, by));
    }

    public static void performMouseHover(WebDriver driver, By by) {
        Actions mouseHover = new Actions(driver);
        mouseHover.moveToElement(driver.findElement(by)).perform();
    }

    public static void performDoubleClick(WebDriver driver, By by) {
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(driver, by)).build().perform();
    }

    public static void waitAndAcceptAlert(WebDriver driver) {
        Wait.waitUntilAlertAppear(driver, Duration.ofSeconds(5)).accept();
        Wait.waitUntilAjaxLoaderDisappear(driver);
    }

    public static void performJsClick(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public static void performClickByJS(WebDriver driver, By by) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", getWebElement(driver, by));
    }


    public static void scrollIntoViewJS(WebDriver driver, By by) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", getWebElement(driver, by));
    }

    public static void scrollIntoViewJS(WebDriver driver, WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView();", webElement);
    }


    public static void scrollUp(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,-250)");

    }

    public static void scrollDown(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("window.scrollBy(0,300)");
    }

    public static void clickButtonIfEnabled(WebDriver driver, By by) throws InterruptedException {
        Wait.time(Wait.ONE_SECOND * 2);
        WebElement element = getWebElement(driver, by);
        if (isButtonClickable(element))
            performClick(element);
    }

    public static String getElementText(WebDriver driver, By by) {
        return getWebElement(driver, by).getText();
    }

    public static String getElementText(WebDriver driver, By by, int elementIndex) {
        return driver.findElements(by).get(elementIndex).getText();
    }

    public static void performClick(WebElement element) {
        element.click();
    }

    public static String getTagValue(WebDriver driver, By by) {
        return getWebElement(driver, by).getAttribute("value");
    }

    public static String getAttributeValue(WebDriver driver, By by, String attribute) {
        return getWebElement(driver, by).getAttribute(attribute);
    }

    public static boolean exists(WebDriver driver, By by) {
        return driver.findElements(by).size() > 0;
    }

    public static void addElementFromCollection(WebDriver driver, By textBox, By addAllButton, List<String> elements) throws InterruptedException {
        for (String element : elements) {
            Controls.clearTextBoxValue(driver, textBox);
            Controls.setTextBoxValue(driver, textBox, element);
            Wait.time(Wait.ONE_SECOND / 2);
            Controls.performClick(driver, addAllButton);
            Wait.time(Wait.ONE_SECOND / 2);
        }
    }

    public static boolean isCheckBoxChecked(WebDriver driver, By by) {
        WebElement checkboxInput = getWebElement(driver, by);
        return checkboxInput.getAttribute("checked") != null &&
                checkboxInput.getAttribute("checked").equalsIgnoreCase("checked");
    }

    public static boolean isButtonClickable(WebElement element) {
        boolean disabled = element.getAttribute("disabled") != null ||
                element.getAttribute("disabled").equalsIgnoreCase("disabled");
        return !disabled;
    }

    public static void setFile(WebDriver driver, By by, String filePath) {
        getWebElement(driver, by).sendKeys(filePath);
    }

    private static final By optionsLocator = By.xpath("//*[@role='option']");

    // select option by displayed text
    public static boolean selectOptionByDisplayedText(WebDriver driver, By select, String displayedText) throws Exception {
        if (displayedText != null && displayedText != "") {
            if (select != null) {
                getWebElement(driver, select).click();
                Wait.time(Wait.ONE_SECOND);
                List<WebElement> options = driver.findElements(optionsLocator);
                if (options.size() != 0) {
                    for (WebElement selectOption : options) {
                        if (selectOption.getText().trim().equalsIgnoreCase(displayedText.trim())) {
                            selectOption.click();
                            return true;
                        }
                    }
                } else {
                    throw new Exception("Drop down list is empty and has no listed options");
                }
            } else
                throw new Exception("Web element 'dropDown' is null .. it could not be located");

        }
        return false;
    }


    // select option by index
    public static void selectOptionByIndex(WebDriver driver, By select, String index) throws Exception {
        if (index != null && index != "") {
            if (select != null) {
                Wait.time(Wait.ONE_SECOND * 3);
                getWebElement(driver, select).click();
                Wait.time(Wait.ONE_SECOND * 3);
                List<WebElement> options = driver.findElements(optionsLocator);
                if (options.size() > Integer.parseInt(index)) {
                    options.get(Integer.parseInt(index)).click();
                } else {
                    throw new Exception("Selected index is out of bound");
                }
            } else
                throw new Exception("Web element 'dropDown' is null .. it could not be located");

        }
    }

    public static void setOptionInDropDownTable(WebDriver driver, By select, String displayedText) throws Exception {
        if (displayedText != null && displayedText != "") {
            if (select != null) {
                getWebElement(driver, select).click();
                List<WebElement> options = driver.findElements(By.xpath("//td"));
                if (options.size() != 0) {
                    for (WebElement selectOption : options)
                        if (selectOption.getText().trim().equalsIgnoreCase(displayedText.trim())) {
                            selectOption.click();
                            break;
                        }
                } else {
                    throw new Exception("Drop down list is empty and has no listed options");
                }
            } else
                throw new Exception("Web element 'dropDown' is null .. it could not be located");

        }
    }

    public static boolean checkIfElementExist(WebDriver driver, By by) {
        List<WebElement> element = driver.findElements(by);
        return element.size() > 0;
    }

    public static String checkIfAlertExist(WebDriver driver) {
        List<WebElement> element = driver.findElements(
                By.xpath("//div[@role='alert']"));
        if (element.size() > 0) {
            String alertMessage = element.get(0).getText();
            for (int i = 1; i < element.size(); i++) {
                alertMessage = alertMessage + "\n" + element.get(i).getText();
            }
            return alertMessage;
        }
        return GeneralConstants.SUCCESS;
    }

    public static void clickOnElementInTableUsingLabel(WebDriver driver, By by, String label) throws Exception {
        List<WebElement> element = driver.findElements(by);
        if (element.size() > 0) {
            for (int i = 0; i < element.size(); i++) {
                if (element.get(i).getText().equalsIgnoreCase(label)) {
                    element.get(i).click();
                    break;
                }
            }
        } else {
            throw new Exception("Table is Empty or can't be found");
        }
    }

    public static void switchToIframeById(WebDriver driver, String frameId) throws Exception {
        driver.switchTo().frame(frameId);
    }

    public static void jsRemoveStyleAttribute(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        List<WebElement> allStyleElements = driver.findElements(By.xpath("//*[@style]"));
        for (WebElement input : allStyleElements) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].removeAttribute('style','style')", input);
        }
    }

    public static void highlightWebElement(WebDriver driver, By by) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Here i pass values based on css style. Yellow background color with solid red color border.
        Wait.time(Wait.ONE_SECOND);
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", getWebElement(driver, by));
    }

    public static void highlightWebElement(WebElement webElement, WebDriver driver) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Here i pass values based on css style. Yellow background color with solid red color border.
        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", webElement);
    }

    public static void clearHighlightWebElement(WebElement webElement, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', '');", webElement, "");
    }

    public static boolean checkIfButtonSelected(WebDriver driver, By by) {
        String originalByLocatorWithSubElement = by.toString() + "//div//span";
        String finalLocator = originalByLocatorWithSubElement.replace("By.xpath:", "");
        WebElement element = driver.findElement(By.xpath(finalLocator));
        return element.getAttribute("class").contains("check");
    }

    public static void acceptAlert(WebDriver driver) {
        driver.switchTo().alert().accept();
    }

    public static void clearBrowserCache(WebDriver driver) throws InterruptedException {
        driver.manage().deleteAllCookies(); //delete all cookies
    }

    public static void waitForPageToLoad(WebDriver driver) throws Exception {
        By spinner = By.xpath("//*[@class='p-progress-spinner-svg']");
        if (checkIfElementExist(driver, spinner)) {
            WebDriverWait wait = new WebDriverWait(driver, Wait.THIRTY_SECOND);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        }
    }

    public static String returnFullClassnameWithChildElementAndPartialClassname(WebDriver driver, By by, String partialClassName) {
        List<WebElement> elements = driver.findElements(by);
        for (WebElement element : elements) {
            if (element.getAttribute("class").contains(partialClassName)) {
                return element.getAttribute("class");
            }
        }
        return null;
    }

    public static void clickKeyboardReturn(WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(org.openqa.selenium.Keys.RETURN).build().perform();
    }

    public static void selectMultipleFromFieldSet(WebDriver driver, By fieldSet, List<String> values, String action){
        if (values != null && values.size() > 0) {
            String fieldSetSubString = fieldSet.toString().replace("By.xpath:", "");
            List<WebElement> optionsSource = driver.findElements(By.xpath(fieldSetSubString + "//div[contains(@class, 'p-picklist-source-wrapper')]//ul/li"));
            List<WebElement> optionsTarget = driver.findElements(By.xpath(fieldSetSubString + "//div[contains(@class, 'p-picklist-target-wrapper')]//ul/li"));
            List<WebElement> actionButtons = driver.findElements(By.xpath(fieldSetSubString + "//div[contains(@class, 'p-picklist-transfer-buttons')]//button"));
            if (action.equalsIgnoreCase(GeneralConstants.ADD_ALL)) {
                Controls.performJsClick(driver, actionButtons.get(1));
            } else if (action.equalsIgnoreCase(GeneralConstants.ADD)) {
                for (int i = 0; i < optionsSource.size(); i++) {
                    for (int j = 0; j < values.size(); j++) {
                        if (values.get(j).equalsIgnoreCase(optionsSource.get(i).getText())) {
                            Controls.performJsClick(driver, optionsSource.get(i));
                            Controls.performJsClick(driver, actionButtons.get(0));
                        }
                    }
                }
            } else if (action.equalsIgnoreCase(GeneralConstants.REMOVE_ALL)) {
                Controls.performJsClick(driver, actionButtons.get(3));
            } else if (action.equalsIgnoreCase(GeneralConstants.REMOVE)) {
                for (int i = 0; i < optionsTarget.size(); i++) {
                    for (int j = 0; j < values.size(); j++) {
                        if (values.get(j).equalsIgnoreCase(optionsTarget.get(i).getText())) {
                            Controls.performJsClick(driver, optionsTarget.get(i));
                            Controls.performJsClick(driver, actionButtons.get(2));
                        }
                    }
                }
            }
        }
    }
}

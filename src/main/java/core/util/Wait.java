package core.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import core.gui.Controls;

import java.time.Duration;

public class Wait {
	public static final long ONE_SECOND = Duration.ofSeconds(1).toMillis();
	public static final long ONE_MINUTE = Duration.ofMinutes(1).toMillis();
	public static final Duration THIRTY_SECOND = Duration.ofSeconds(30);
	public static final Duration TWO_SECOND_DURATION = Duration.ofSeconds(2);
	public static final Duration TEN_SECOND_DURATION = Duration.ofSeconds(10);
	public static final Duration ONE_MINUTE_DURATION = Duration.ofSeconds(60);
	public static final Duration TWO_MINUTE_DURATION = Duration.ofSeconds(120);
	public static final By AJAX_LOADER_LOCATOR = By.xpath("//*[@class='pi pi-spin pi-spinner ajax-loader']");
	public static final By LOADING_LOGO_LOCATOR = By.xpath("//*[.='Loading...']");


	public static void time(long millis) throws InterruptedException {
		Thread.sleep(millis);
	}

    public static void elementVisible(WebDriver driver, By by, Duration duration) {
		new WebDriverWait(driver, duration).until(ExpectedConditions.visibilityOf(Controls.getWebElement(driver, by)));
    }


	public static WebElement waitUntilElementToBeVisible(WebDriver driver  , By locator , Duration duration)  {
		return new WebDriverWait(driver, duration ).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static Boolean waitUntilElementTextContains(WebDriver driver  , By locator ,String value, Duration duration)  {
		return new WebDriverWait(driver, duration ).until(ExpectedConditions.textToBe(locator,value));
	}

	public static WebElement waitUntilElementToBeVisible(WebDriver driver  , WebElement webElement , Duration duration)  {
		return new WebDriverWait(driver, duration ).until(ExpectedConditions.visibilityOf(webElement));
	}

	public static WebElement waitUntilElementToBeClickable(WebDriver driver  , By locator , Duration duration)  {
		return new WebDriverWait(driver, duration ).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static Boolean waitUntilElementToDisappear(WebDriver driver  , By locator , Duration duration)  {
		return new WebDriverWait(driver, duration ).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	public static Alert waitUntilAlertAppear(WebDriver driver, Duration duration)  {
		return new WebDriverWait(driver,duration).until(ExpectedConditions.alertIsPresent());
	}


	public static Boolean waitUntilAjaxLoaderDisappear(WebDriver driver)  {
		return new WebDriverWait(driver, ONE_MINUTE_DURATION ).until(ExpectedConditions.invisibilityOfElementLocated(AJAX_LOADER_LOCATOR));
	}

	public static Boolean waitUntilLoadingLogoDisappear(WebDriver driver)  {
		return new WebDriverWait(driver, ONE_MINUTE_DURATION ).until(ExpectedConditions.invisibilityOfElementLocated(LOADING_LOGO_LOCATOR));
	}

	public static Boolean waitUntilLoadersDisappear(WebDriver driver)  {
		return waitUntilAjaxLoaderDisappear(driver)&& waitUntilLoadingLogoDisappear(driver);
	}





	public static boolean waitForJStoLoad(WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		// wait for jQuery to load
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long)executor.executeScript("return jQuery.active") == 0);
				}
				catch (Exception e) {
					return true;
				}
			}
		};

		// wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return executor.executeScript("return document.readyState")
						.toString().equals("complete");
			}
		};

		return wait.until(jQueryLoad) && wait.until(jsLoad);
	}


}

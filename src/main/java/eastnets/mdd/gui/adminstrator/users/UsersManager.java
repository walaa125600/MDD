package eastnets.mdd.gui.adminstrator.users;

import core.gui.Controls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UsersManager {
    private static final By ADD_BUTTON = By.xpath("//*[@title='Add User']//button");
    private static final By USERNAME_FILTER_LOCATOR = By.xpath("(//p-columnfilterformelement/input)[1]");
    private static final By FILTER_ICON = By.xpath("(//filtericon)[1]");
    private static final By APPROVE_BUTTON = By.xpath("//*[@title='Approve']");
    private static final By ENABLE_BUTTON = By.xpath("//*[@title='Enable']");
    private static final By YES_BUTTON = By.xpath("//*[text()='Yes']");
    private static final By LAST_PAGE_ARROW = By.xpath("//button[@aria-label='Last Page']");
    private static final By NEXT_ARROW = By.xpath("//button[@aria-label='Next Page']");

    private static final By EQUAL_SELECTION_LOCATOR = By.xpath("//li[text()=' Equals ']");
    private static final By NUMBER_OF_PAGES = By.xpath("//span[@class='p-paginator-current ng-star-inserted']");
    private static final By DISAPPROVE_BUTTON_LOCATOR = By.xpath("//*[@title='Disapprove']");
    private static final By SEARCH_ROW_RESULT = By.xpath("(//tr[@class='ng-star-inserted'])[3]");
    private static final By ALL_ROWS_LOCATOR = By.xpath("//tr/td/span/a");
    private static final By EDIT_BUTTON = By.xpath("//span[@class='pi pi-user-edit p-button-icon ng-star-inserted']");

    public static boolean flag = false;
    private static final By CHECKING_DISAPPROVE_BUTTON = By.xpath("//mdd-options//span[5]/p-button");

    public static void clickAddUserButton(WebDriver driver) throws Exception {
        Controls.performClick(driver, ADD_BUTTON);
        Controls.waitForPageToLoad(driver);
    }

    public static void setUserNameFilter(WebDriver driver, String username) throws Exception {
        Controls.waitForPageToLoad(driver);
        Controls.setTextBoxValue(driver, USERNAME_FILTER_LOCATOR, username);

    }

    public static void clickFilter(WebDriver driver) throws Exception {
        //Controls.waitForPageToLoad(driver);
        Controls.performClick(driver, FILTER_ICON);
        Controls.performClick(driver, EQUAL_SELECTION_LOCATOR);
    }

    public static void clickOnLastPage(WebDriver driver) throws Exception {

        Controls.waitForPageToLoad(driver);
        Controls.waitAndClick(driver, LAST_PAGE_ARROW, Duration.ofSeconds(30));
        Controls.scrollUp(driver);

    }

    public static void clickApproveBtn(WebDriver driver) throws Exception {
        Controls.performClick(driver, APPROVE_BUTTON);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickEnableBtn(WebDriver driver) throws Exception {
        Controls.performClick(driver, ENABLE_BUTTON);
        Controls.waitForPageToLoad(driver);
    }


    public static void clickYesButton(WebDriver driver) throws Exception {
        Controls.performClick(driver, YES_BUTTON);
        Controls.waitForPageToLoad(driver);
    }

    public static void clickDisApproveBtn(WebDriver driver) throws Exception {
        Controls.performClick(driver, APPROVE_BUTTON);
        Controls.waitForPageToLoad(driver);
    }


    public static void searchForSpecificUserAndApproved(WebDriver driver, String username) throws Exception {
        String Username = username;
        String NumberOfPages = Controls.getElementText(driver, NUMBER_OF_PAGES);
        System.out.println(NumberOfPages);
        String[] parts = NumberOfPages.split("of");
        String numberAfterOf = parts[1].trim();
        int digitAfterOf = Integer.parseInt(numberAfterOf);
        int i = 0;
        System.out.println(digitAfterOf);


        do {
            for (WebElement element : driver.findElements(ALL_ROWS_LOCATOR)) {
                // System.out.println(element.getText());
                if (element.getText().contains(Username)) {
                    System.out.println(element.getText());
                    System.out.println("will click Approved ");

                    List<String> stringList = new ArrayList<>();
                    for (WebElement TXT : driver.findElements(ALL_ROWS_LOCATOR)) {
                        stringList.add(TXT.getText());

                    }
                    int index = (stringList.indexOf(element.getText()) + 1);
                    System.out.println("index = " + index);
                    Controls.performClick(driver, By.xpath("(//*[@title='Approve'])[" + index + "]"));
                    // Controls.performClick(driver, By.xpath("//span[@class='pi pi-user-edit p-button-icon ng-star-inserted'])[" + index + "]"));
                    System.out.println("User Approved ");
                    break;
                }

            }

            System.out.println(i);
            i++;
            System.out.println("will click next ");
            Controls.performJsClick(driver, driver.findElement(NEXT_ARROW));
            Controls.waitForPageToLoad(driver);
            Thread.sleep(2000);
            System.out.println("next clicked ");
        }
        while (i != digitAfterOf);


    }


    public static void searchForSpecificUserAndEdit(WebDriver driver, String username) throws Exception {


        String Username = username;
        System.out.println("Username: "+username);
        String NumberOfPages = Controls.getElementText(driver, NUMBER_OF_PAGES);
        System.out.println(NumberOfPages);
        String[] parts = NumberOfPages.split("of");
        String numberAfterOf = parts[1].trim();
        int digitAfterOf = Integer.parseInt(numberAfterOf);
        int i = 0;
        System.out.println(digitAfterOf);


        do {
            for (WebElement element : driver.findElements(ALL_ROWS_LOCATOR)) {
                // System.out.println(element.getText());
                if (element.getText().contains(Username)) {
                    System.out.println(element.getText());
                    System.out.println("will click Approved ");

                    List<String> stringList = new ArrayList<>();
                    for (WebElement TXT : driver.findElements(ALL_ROWS_LOCATOR)) {
                        stringList.add(TXT.getText());

                    }
                    int index = (stringList.indexOf(element.getText()) + 1);
                    System.out.println("index = " + index);
                    Controls.performClick(driver, By.xpath("(//*[@title='Approve'])[" + index + "]"));
                    // Controls.performClick(driver, By.xpath("//span[@class='pi pi-user-edit p-button-icon ng-star-inserted'])[" + index + "]"));
                    System.out.println("User Approved ");
                    break;
                }

            }

            System.out.println(i);
            i++;
            System.out.println("will click next ");
            Controls.performJsClick(driver, driver.findElement(NEXT_ARROW));
            Controls.waitForPageToLoad(driver);
            Thread.sleep(2000);
            System.out.println("next clicked ");
        }
        while (i != digitAfterOf);


    }

    public static void clickOnDisapprove(WebDriver driver) {
        Controls.performClick(driver, DISAPPROVE_BUTTON_LOCATOR);
    }


    public static boolean checkApproveOrDisapproveStatus(WebDriver driver) throws Exception {
        if (driver.findElement(CHECKING_DISAPPROVE_BUTTON).getAttribute("title").equals("Disapprove")) {
            clickOnDisapprove(driver);
            Controls.waitForPageToLoad(driver);
            if (driver.findElement(CHECKING_DISAPPROVE_BUTTON).getAttribute("title").equals("Approve")) {
                flag = true;
            } else {
                flag = false;
            }
        } else if (driver.findElement(CHECKING_DISAPPROVE_BUTTON).getAttribute("title").equals("Approve")) {
            clickDisApproveBtn(driver);
            Controls.waitForPageToLoad(driver);


            if (driver.findElement(CHECKING_DISAPPROVE_BUTTON).getAttribute("title").equals("Disapprove")) {
                flag = true;
            } else {
                flag = false;
            }

        }
        return flag;
    }

}

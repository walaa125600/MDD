package eastnets.mdd.ui;

import com.paypal.selion.platform.dataprovider.DataProviderFactory;
import com.paypal.selion.platform.dataprovider.DataResource;
import com.paypal.selion.platform.dataprovider.SeLionDataProvider;
import com.paypal.selion.platform.dataprovider.impl.InputStreamResource;
import core.BaseTest;
import core.ExceptionHandler;
import core.constants.mdd.GeneralConstants;
import core.util.Log;
import core.util.Property;
import eastnets.common.gui.LoginPage;
import eastnets.common.gui.Navigation;
import eastnets.mdd.control.AdminSettingsControl;
import eastnets.mdd.entity.Administrator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class AdminSettingsTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void login() {
        try {
            LoginPage.login(driver
                    , Property.fromFile(GeneralConstants.TEST_CONFIG_FILE_NAME).getProperty("login.mdd.userMail")
                    , Property.fromFile(GeneralConstants.TEST_CONFIG_FILE_NAME).getProperty("login.mdd.password"));
        } catch (Exception e) {
            ExceptionHandler.onExceptionRaised(e
                    , new Object() {
                    }.getClass().getName()
                    , new Object() {
                    }.getClass().getEnclosingMethod().getName());
        }
    }

    @BeforeMethod
    public void navigateToAdminSettings() {
        try {
            Log.info("Navigate to Admin Settings module.");
            Navigation.ADMINISTRATION_SETTINGS.navigate(driver);
        } catch (Exception e) {
            ExceptionHandler.onExceptionRaised(e
                    , new Object() {
                    }.getClass().getName()
                    , new Object() {
                    }.getClass().getEnclosingMethod().getName());
        }
    }

    @Test()
    public void uploadXsd() {

        try {
            //Create extent test to be logged in report using test case title
            test = extent.createTest("# test case id - Verify that XSDs are getting uploaded");
            Log.test = test;
            Log.startTestCase("Verify that XSDs are getting uploaded");
            String actual = AdminSettingsControl.uploadXsd(driver,
                    browserDefaultUploadPath + "xsdFile.xsd");
            Assert.assertEquals(actual,
                    GeneralConstants.SUCCESS,
                    GeneralConstants.POM_EXCEPTION_ERR_MSG + "with message" + actual);
            Log.endTestCase("Verify that XSDs are getting uploaded");
        } catch (Exception e) {
            ExceptionHandler.onExceptionRaised(e
                    , new Object() {
                    }.getClass().getName()
                    , new Object() {
                    }.getClass().getEnclosingMethod().getName());
        }
    }

    @Test(dataProvider = "searchAdminSettings")
    public void searchAdminSettings(Administrator administrator) {

        try {
            //Create extent test to be logged in report using test case title
            test = extent.createTest("Verify that XSDs are getting uploaded");
            Log.test = test;
            Log.startTestCase("Verify that XSDs are getting uploaded");
            String searchResult = AdminSettingsControl.filterWithCriteria(driver, administrator.getFiltrationCriteria(), administrator.getFiltrationValue());
            Assert.assertTrue(searchResult.contains(administrator.getFiltrationValue()), GeneralConstants.POM_EXCEPTION_ERR_MSG);
            Log.endTestCase("Verify that XSDs are getting uploaded");
        } catch (Exception e) {
            ExceptionHandler.onExceptionRaised(e
                    , new Object() {
                    }.getClass().getName()
                    , new Object() {
                    }.getClass().getEnclosingMethod().getName());
        }
    }


    @DataProvider(name = "searchAdminSettings")
    public Object[][] uploadMessageFromMessagePartner() throws IOException {
        String dataFilePath = userDirectoryPath + testDataConfigsProps.getProperty("searchAdminSettings");
        DataResource resource =
                new InputStreamResource(new FileInputStream(dataFilePath),
                        Administrator.class, "json");
        SeLionDataProvider dataProvider =
                DataProviderFactory.getDataProvider(resource);
        return dataProvider.getAllData();
    }


}

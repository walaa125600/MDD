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
import eastnets.mdd.control.AdminProfileControl;
import eastnets.mdd.control.AdminSettingsControl;
import eastnets.mdd.entity.Administrator;
import eastnets.mdd.entity.Profile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class AdminProfileTest extends BaseTest {

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
    public void navigateToAdminProfile() {
        try {
            Log.info("Navigate to Admin Profile module.");
            Navigation.ADMINISTRATION_PROFILE.navigate(driver);
        } catch (Exception e) {
            ExceptionHandler.onExceptionRaised(e
                    , new Object() {
                    }.getClass().getName()
                    , new Object() {
                    }.getClass().getEnclosingMethod().getName());
        }
    }

    @Test(dataProvider = "addProfile")
    public void addProfile(Profile profile) {

        try {
            //Create extent test to be logged in report using test case title
            test = extent.createTest("# test case id - Verify that profiles are getting created and the data display under profile module as added");
            Log.test = test;
            Log.startTestCase("Verify that profiles are getting created and the data display under profile module as added");
            AdminProfileControl.addNewProfile(driver,profile );
            Log.endTestCase("Verify that XSDs are getting uploaded");
        } catch (Exception e) {
            ExceptionHandler.onExceptionRaised(e
                    , new Object() {
                    }.getClass().getName()
                    , new Object() {
                    }.getClass().getEnclosingMethod().getName());
        }
    }



    @DataProvider(name = "addProfile")
    public Object[][] addProfileDp() throws IOException {
        String dataFilePath = userDirectoryPath + testDataConfigsProps.getProperty("addProfile");
        DataResource resource =
                new InputStreamResource(new FileInputStream(dataFilePath),
                        Profile.class, "json");
        SeLionDataProvider dataProvider =
                DataProviderFactory.getDataProvider(resource);
        return dataProvider.getAllData();
    }


}

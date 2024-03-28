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
import core.util.ServerUtil;
import eastnets.common.gui.LoginPage;
import eastnets.common.gui.Navigation;
import eastnets.mdd.control.DetectionEditorControl;
import eastnets.mdd.control.DetectionManagerControl;
import eastnets.mdd.entity.Duplicate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ConfigurationManagerTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void login() {
        try {
            LoginPage.login(driver
                    , Property.fromFile(GeneralConstants.TEST_CONFIG_FILE_NAME).getProperty("login.mdd.userMail")
                    , Property.fromFile(GeneralConstants.TEST_CONFIG_FILE_NAME).getProperty("login.mdd.password"));
        }
        catch (Exception e)
        {
            ExceptionHandler.onExceptionRaised(e
                    ,new Object(){}.getClass().getName()
                    ,new Object(){}.getClass().getEnclosingMethod().getName());
        }
    }


    @Test(dataProvider = "uploadMessageFromMessagePartner", groups = {"poc"})
    public void uploadMessageFromMessagePartner(Duplicate duplicate) {

        try {
            //Create extent test to be logged in report using test case title
            test = extent.createTest(duplicate.getTestCaseTitle());
            Log.test = test;
            Log.startTestCase(duplicate.getTestCaseTitle());
            ServerUtil.transferFileToServer(GeneralConstants.UPLOAD_FILE_PATH+duplicate.getFileName(),
                    Property.fromFile(core.constants.saa.GeneralConstants.GENERAL_CONFIG_FILE_NAME).getProperty(core.constants.saa.GeneralConstants.RJE_MESSAGE_INPUT_FILE_LOCATION),
                    GeneralConstants.SAA_APP_NAME);
            Log.info("Waiting for Saa to process the file.");
            //Wait.time(Wait.ONE_MINUTE);
            Log.info("Navigate to Detection Manager module.");
            Navigation.DUPLICATES_MANAGER.navigate(driver);
            DetectionManagerControl.prepareForFiltration(driver);
            DetectionManagerControl.selectSource(driver, duplicate.getSource());
            DetectionManagerControl.insertReference(driver, duplicate.getFinMessage().getReference());
            DetectionManagerControl.selectStatus(driver, duplicate.getStatus());
            DetectionManagerControl.clickSearchButton(driver);

            Assert.assertTrue(DetectionManagerControl.readDetectionStatusInTable(driver).
                    equalsIgnoreCase(duplicate.getFinMessage().getDetectionStatus()),
                    GeneralConstants.POM_EXCEPTION_ERR_MSG);

            DetectionManagerControl.clickFirstDetectionId(driver);

            Assert.assertTrue(DetectionEditorControl.readDetectionStatus(driver).
                            equalsIgnoreCase(duplicate.getFinMessage().getDetectionStatus()),
                    GeneralConstants.POM_EXCEPTION_ERR_MSG);

            Assert.assertTrue(DetectionEditorControl.clickAccept(driver).
                            contains(duplicate.getExpectedMassage()),
                    GeneralConstants.POM_EXCEPTION_ERR_MSG);

            Log.endTestCase(duplicate.getTestCaseTitle());

        } catch (Exception e) {
            ExceptionHandler.onExceptionRaised(e
                    , new Object() {
                    }.getClass().getName()
                    , new Object() {
                    }.getClass().getEnclosingMethod().getName());
        }
    }




    @DataProvider(name = "uploadMessageFromMessagePartner")
    public Object[][] uploadMessageFromMessagePartner() throws IOException {
        String dataFilePath = userDirectoryPath + testDataConfigsProps.getProperty("uploadMessageFromMessagePartner");
        DataResource resource =
                new InputStreamResource(new FileInputStream(dataFilePath),
                        Duplicate.class, "json");
        SeLionDataProvider dataProvider =
                DataProviderFactory.getDataProvider(resource);
        return dataProvider.getAllData();
    }


}

package eastnets.mdd.control;

import core.constants.mdd.GeneralConstants;
import core.gui.Controls;
import core.util.Log;
import eastnets.mdd.entity.Profile;
import eastnets.mdd.gui.adminstrator.profile.AddProfile;
import eastnets.mdd.gui.adminstrator.profile.ProfileManager;
import eastnets.mdd.gui.adminstrator.settings.SettingsManager;
import org.openqa.selenium.WebDriver;

public class AdminProfileControl {

    public static String addNewProfile(WebDriver driver, Profile profile) throws Exception {
        Log.info("click on add new profile button");
        ProfileManager.clickAddProfileBtn(driver);
        Log.info("fill the profile name");
        AddProfile.fillProfileName(driver, profile.getName());
        Log.info("fill the profile description");
        AddProfile.fillDescription(driver, profile.getDescription());
        Log.info("click the authorize button");
        AddProfile.clickAuthorizeBtn(driver, profile.getAuthorization());
        Log.info("fill the configuration manager");
        AddProfile.setConfigurationFieldset(driver, profile.getAuthorization().getConfigurationManagerActions(), GeneralConstants.ADD);
        Log.info("fill the duplication manager");
        AddProfile.setDuplicatesFieldset(driver, profile.getAuthorization().getDuplicatesManagerActions(), GeneralConstants.ADD);
        Log.info("fill the available bics");
        AddProfile.setBicCodesFieldset(driver, profile.getAuthorization().getAvailableBics(), GeneralConstants.ADD);
        Log.info("fill the available units");
        AddProfile.setUnitsFieldset(driver, profile.getAuthorization().getAvailableUnits(), GeneralConstants.ADD);
        Log.info("fill the available categories");
        AddProfile.setCategoriesFieldset(driver, profile.getAuthorization().getAvailableCategories(), GeneralConstants.ADD);
        Log.info("click the roles button");
        AddProfile.clickRolesBtn(driver, profile.getRoles());
        Log.info("fill the roles ");
        AddProfile.setRolesFieldset(driver, profile.getRoles().getRoles(), GeneralConstants.ADD);
        Log.info("click the save button");
        AddProfile.clickSaveBtn(driver);

        return Controls.checkIfAlertExist(driver);
    }

}


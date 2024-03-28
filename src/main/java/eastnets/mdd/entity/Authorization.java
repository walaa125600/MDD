package eastnets.mdd.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Authorization extends Main {

    public ArrayList<String> getConfigurationManagerActions() {
        return configurationManagerActions;
    }

    public void setConfigurationManagerActions(ArrayList<String> configurationManagerActions) {
        this.configurationManagerActions = configurationManagerActions;
    }

    public ArrayList<String> getDuplicatesManagerActions() {
        return duplicatesManagerActions;
    }

    public void setDuplicatesManagerActions(ArrayList<String> duplicatesManagerActions) {
        this.duplicatesManagerActions = duplicatesManagerActions;
    }

    public ArrayList<String> getAvailableBics() {
        return availableBics;
    }

    public void setAvailableBics(ArrayList<String> availableBics) {
        this.availableBics = availableBics;
    }

    public ArrayList<String> getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(ArrayList<String> availableUnits) {
        this.availableUnits = availableUnits;
    }

    public ArrayList<String> getAvailableCategories() {
        return availableCategories;
    }

    public void setAvailableCategories(ArrayList<String> availableCategories) {
        this.availableCategories = availableCategories;
    }

    public ArrayList<String> getAuthorizedCategories() {
        return authorizedCategories;
    }

    public void setAuthorizedCategories(ArrayList<String> authorizedCategories) {
        this.authorizedCategories = authorizedCategories;
    }

    private ArrayList<String> configurationManagerActions;
    private ArrayList<String> duplicatesManagerActions;
    private ArrayList<String> availableBics;
    private ArrayList<String> availableUnits;
    private ArrayList<String> availableCategories;
    private ArrayList<String> authorizedCategories;


}

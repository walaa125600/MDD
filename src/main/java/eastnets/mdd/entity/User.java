package eastnets.mdd.entity;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Main {
    private String authenticationMethod;
    private String username;
    private String fullUserName;
    private String userEmail;
    private String selectProfile;
    private String selectAuthenticator;
    private String expirationDate;
    private boolean autoGenerate;
    private String password;
    private String confirmPassword;
    private String saveButton;
    private String continueLogin;
    private String messageForUserName;
    private String messageForProfile;
    private String messageForEmail;
    private String messageForPassword;
    private String ApproveButton;
    private String EnableButton;
    private  String UserProfileName;
    private String LogoutButton;

    public String getUserEmailEDIT() {
        return UserEmailEDIT;
    }

    public void setUserEmailEDIT(String userEmailEDIT) {
        UserEmailEDIT = userEmailEDIT;
    }

    private String UserEmailEDIT;

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    private String  username2;


    public String getUserProfileName() {
        return UserProfileName;
    }

    public void setUserProfileName(String userProfileName) {
        UserProfileName = userProfileName;
    }



    public String getApproveButton() {
        return ApproveButton;
    }

    public void setApproveButton(String approveButton) {
        ApproveButton = approveButton;
    }



    public String getEnableButton() {
        return EnableButton;
    }

    public void setEnableButton(String enableButton) {
        EnableButton = enableButton;
    }



    public String getLogoutButton() {
        return LogoutButton;
    }

    public void setLogoutButton(String logoutButton) {
        LogoutButton = logoutButton;
    }




    public String getAuthenticationMethod() {
        return authenticationMethod;
    }

    public void setAuthenticationMethod(String authenticationMethod) {
        this.authenticationMethod = authenticationMethod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getSelectProfile() {
        return selectProfile;
    }

    public void setSelectProfile(String selectProfile) {
        this.selectProfile = selectProfile;
    }

    public String getSelectAuthenticator() {
        return selectAuthenticator;
    }

    public void setSelectAuthenticator(String selectAuthenticator) {
        this.selectAuthenticator = selectAuthenticator;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isAutoGenerate() {
        return autoGenerate;
    }

    public void setAutoGenerate(boolean autoGenerate) {
        this.autoGenerate = autoGenerate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(String saveButton) {
        this.saveButton = saveButton;
    }

    public String getContinueLogin() {
        return continueLogin;
    }

    public void setContinueLogin(String continueLogin) {
        this.continueLogin = continueLogin;
    }

    public String getMessageForUserName() {
        return messageForUserName;
    }

    public void setMessageForUserName(String messageForUserName) {
        this.messageForUserName = messageForUserName;
    }

    public String getMessageForProfile() {
        return messageForProfile;
    }

    public void setMessageForProfile(String messageForProfile) {
        this.messageForProfile = messageForProfile;
    }

    public String getMessageForEmail() {
        return messageForEmail;
    }

    public void setMessageForEmail(String messageForEmail) {
        this.messageForEmail = messageForEmail;
    }

    public String getMessageForPassword() {
        return messageForPassword;
    }

    public void setMessageForPassword(String messageForPassword) {
        this.messageForPassword = messageForPassword;
    }
}

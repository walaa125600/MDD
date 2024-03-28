package saa.entity;

public class Main {

    public String getExpectedMassage() {
        return expectedMassage;
    }

    public void setExpectedMassage(String expectedMassage) {
        this.expectedMassage = expectedMassage;
    }

    public String getTestCaseTitle() {
        return testCaseTitle;
    }

    public void setTestCaseTitle(String testCaseTitle) {
        this.testCaseTitle = testCaseTitle;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private String testMethod;
    private String testCaseTitle;
    private String action;
    private String expectedMassage;



}

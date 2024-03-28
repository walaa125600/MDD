package eastnets.common.control;

public enum Application {
    ADMIN("scdb", "scdb"),
    SFP("SafeWatchDB", "swserver"),SAA("SafeWatchDB", "swserver");

    private final String databaseName;
    private final String databaseUserName;

    Application(String databaseName, String databaseUserName) {
        this.databaseName = databaseName;
        this.databaseUserName = databaseUserName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseUserName() {
        return databaseUserName;
    }
}

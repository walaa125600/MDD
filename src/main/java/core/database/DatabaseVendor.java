package core.database;

import java.util.Arrays;

public enum DatabaseVendor {
    SQLSERVER,
    ORACLE;

    public static DatabaseVendor ofName(String name) {
        return Arrays.stream(DatabaseVendor.values())
                .filter(databaseVendor -> databaseVendor.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong vendor name"));
    }
}

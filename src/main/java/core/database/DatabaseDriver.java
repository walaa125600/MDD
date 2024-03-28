package core.database;

import core.constants.mdd.GeneralConstants;
import core.util.Log;
import core.util.Property;
import eastnets.common.control.Application;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class DatabaseDriver {

    private static String getUrlFromProperties(Properties connectionProperties, Application application) {
        String url = connectionProperties.getProperty("db.url");
        DatabaseVendor vendor = DatabaseVendor.ofName(url.split(":")[1]);
        if (vendor == DatabaseVendor.SQLSERVER)
            url += String.format(";databaseName=%s", application.getDatabaseName());
        return url;
    }

    public static Connection getConnection(Application application) throws SQLException {
        Properties connectionProperties = Property.fromFile(GeneralConstants.GENERAL_CONFIG_FILE_NAME);
        String enDatabaseUser = application.getDatabaseUserName();

        return DriverManager.getConnection(
                getUrlFromProperties(connectionProperties, application),
                enDatabaseUser,
                connectionProperties.getProperty(String.format("db.%s.password", enDatabaseUser))
        );
    }


    public static ResultSet executeQueryAndGetRS(Connection conn, String sqlQuery) throws SQLException
    {
        Log.info("*********      Executing DB Query     ************");
        Log.info(sqlQuery);

        Statement statement = conn.createStatement();
        ResultSet rs =  statement.executeQuery(sqlQuery);

        Log.info("************     DB Query executed successfully     ***************");

        return rs;
    }

    public static int executeUpdate(Connection conn, String sqlQuery) throws SQLException
    {
        Log.info("*********      Executing DB Query     ************");
        Log.info(sqlQuery);

        Statement statement = conn.createStatement();
        int effectedRowsNumber =  statement.executeUpdate(sqlQuery);
        conn.commit();

        Log.info("************     DB Query executed successfully     ***************");

        return effectedRowsNumber;
    }

    public static void closeDBConnection(Connection con) throws SQLException {
        Log.info("******     Closing DB Connection      *******");
        con.close();
        Log.info("******      DB Connection closed successfully     *******");
    }
    public static int getCountResult(Connection connection, String query, List<Object> parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        int index = 1;
        for (Object parameter: parameters) {
            statement.setObject(index++, parameter);
        }
        try (ResultSet resultSet = statement.executeQuery()) {
            if (!resultSet.next()) throw new SQLException("Should have returned at least the count value");
            return resultSet.getInt(1);
        }
    }

    public static int getCountResult(Connection connection, String query, Object parameter) throws SQLException {
        Log.info(String.format("Executing query '%s' with parameter '%s'", query, parameter));
        return getCountResult(connection, query, Collections.singletonList(parameter));
    }

}

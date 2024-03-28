package eastnets.mdd.backendServices.database.daos;

import core.database.DatabaseDriver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DuplicationManagerDAO {


    public String getEntityName(Connection dbConn,String listName) throws SQLException {
        //Create DB Query to delete profile from listSet
        String query = "select\n" +
                "tblen.name as entityName\n" +
                "from tBlackListEntryNames tblen\n" +
                "left join tBlackListEntries tble on tblen.entry_id = tble.id\n" +
                "left join tBlackListVersions tblv on tble.black_list_id = tblv.id\n" +
                "left join tBlackLists tbl on tblv.black_list_id = tbl.id\n" +
                "where \n" +
                "tbl.display_name like '%" + listName + "%'";
        // Execute query
        ResultSet rs = DatabaseDriver.executeQueryAndGetRS(dbConn, query);
        String entityName = "";
        if (rs.next()) {
            entityName = rs.getString("entityName") == null ? "" : rs.getString("entityName");
        }
        return entityName;
    }


}

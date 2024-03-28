package eastnets.mdd.backendServices.database.services;

import core.database.DatabaseDriver;
import eastnets.common.control.Application;
import eastnets.mdd.backendServices.database.daos.DuplicationManagerDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DuplicationManagerService {

    public String getEntityNameFromList(String listName) throws SQLException, IOException {
        //Open connection to users database
        Connection connection = DatabaseDriver.getConnection(Application.SFP);
        DuplicationManagerDAO entityDAO = new DuplicationManagerDAO();
        String entityName = entityDAO.getEntityName(connection,listName);
        //close db connection
        DatabaseDriver.closeDBConnection(connection);
        return entityName;
    }
}

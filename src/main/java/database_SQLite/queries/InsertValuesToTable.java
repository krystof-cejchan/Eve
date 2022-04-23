package database_SQLite.queries;

import database_SQLite.DatabaseManager;

import java.sql.SQLException;

public class InsertValuesToTable extends DatabaseManager {
    public void insertValuesToTable(String pathToBeAdded) throws SQLException, ClassNotFoundException {
        System.out.println(new CheckIfRecordExists().doesRecordExist(null, null, pathToBeAdded));
        if (!new CheckIfRecordExists().doesRecordExist(null, null, pathToBeAdded)) {
            String query = "INSERT INTO audio_file_locations (path)\n" +
                    "VALUES('" + pathToBeAdded + "');";


            new CommitQuery().commitThisQuery(query);
        }
    }
}
package cz.krystofcejchan.database_SQLite.file_database.queries;

import cz.krystofcejchan.database_SQLite.file_database.DatabaseManager;

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
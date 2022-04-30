package database_SQLite.analytics.queries;

import database_SQLite.DatabaseManager;

import javax.annotation.Nullable;
import java.sql.SQLException;

public class InsertValuesToTable extends DatabaseManager {
    public void insertValuesToTable(@Nullable String table, String column, String value) throws SQLException, ClassNotFoundException {
        if (column == null || value == null)
            return;
        if (table == null)
            table = "commands";
        System.out.println(new CheckIfRecordExists().doesRecordExist(table, column, value));
        if (!new CheckIfRecordExists().doesRecordExist(table, column, value)) {
            String query = "INSERT INTO " + table + " (" + column + ")\n" +
                    "VALUES('" + value + "');";


            new CommitQuery().commitThisQuery(query);
        }
    }
}
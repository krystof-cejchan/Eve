package database_SQLite.analytics.queries;

import database_SQLite.DatabaseManager;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckIfRecordExists extends DatabaseManager {


    public boolean doesRecordExist(@Nullable String table, @Nullable String column, String value) throws SQLException, ClassNotFoundException {
        if (table == null)
            table = "commands";
        if (column == null)
            throw new SQLException("column must not be null or empty");

        PreparedStatement prepared = super.connectToDatabase().prepareStatement("SELECT * FROM " + table + " WHERE " + column + " = ?;");
        prepared.setString(1, value);
        return prepared.executeQuery().next();
    }
}

package database_SQLite.file_database.queries;

import database_SQLite.file_database.DatabaseManager;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckIfRecordExists extends DatabaseManager {


    public boolean doesRecordExist(@Nullable String table, @Nullable String column, String value) throws SQLException, ClassNotFoundException {
//SELECT * FROM audio_file_locations WHERE path = 'D:\USERS_INPUT_AUDIO\52328115156182631'
        if (table == null)
            table = "audio_file_locations";
        if (column == null)
            column = "path";

        PreparedStatement prepared = super.connectToDatabase().prepareStatement("SELECT * FROM " + table + " WHERE " + column + " = ?;");
        prepared.setString(1, value);
        return prepared.executeQuery().next();
    }
}

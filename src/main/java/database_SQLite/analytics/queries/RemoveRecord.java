package database_SQLite.analytics.queries;

import database_SQLite.DatabaseManager;

import javax.annotation.Nullable;
import java.sql.SQLException;

public class RemoveRecord extends DatabaseManager {


    public void removeRecordFromDatabasebyID(@Nullable String table, int id) throws SQLException {
        if (table == null)
            table = "audio_file_locations";

        String query = "DELETE FROM " + table + " WHERE id='" + id + "';";


        new CommitQuery().commitThisQuery(query);
    }

    public void removeRecordFromDatabasebyRecord(@Nullable String table, String column, String record) throws SQLException {
        //DELETE FROM audio_file_locations WHERE path = 'D:\USERS_INPUT_AUDIO\523281151561826315.wav';

        if (table == null)
            table = "commands";
        System.out.println(record);
        String query = "DELETE FROM " + table + " WHERE " + column + "='" + record + "';";


        new CommitQuery().commitThisQuery(query);
    }

    public void removeAllRecordsFromDatabase(@Nullable String table) throws SQLException {
        if (table == null)
            table = "commands";
        String query = "DELETE FROM " + table + ";";


        new CommitQuery().commitThisQuery(query);

    }
}

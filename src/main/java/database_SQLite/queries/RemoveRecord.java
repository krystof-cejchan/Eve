package database_SQLite.queries;

import database_SQLite.DatabaseManager;

import javax.annotation.Nullable;
import java.sql.SQLException;

public class RemoveRecord extends DatabaseManager {
    public void removeRecordFromDatabasebyID(@Nullable String table, int id) throws SQLException {
        if (table == null)
            table = "audio_file_locations";

        String query = "DELETE FROM " + table + " WHERE id=" + id + ";";


        new CommitQuery().commitThisQuery(query);
    }
    public void removeRecordFromDatabasebyRecord(@Nullable String table, String record) throws SQLException {
        if (table == null)
            table = "audio_file_locations";

        String query = "DELETE FROM " + table + " WHERE path=" + record + ";";


        new CommitQuery().commitThisQuery(query);
    }

    public void removeAllRecordsFromDatabase(@Nullable String table) throws SQLException {
        if (table == null)
            table = "audio_file_locations";
        String query = "DELETE FROM " + table + ";";


        new CommitQuery().commitThisQuery(query);

    }
}

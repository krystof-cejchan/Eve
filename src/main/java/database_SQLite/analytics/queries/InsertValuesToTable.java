package database_SQLite.analytics.queries;

import database_SQLite.analytics.DatabaseManager;

import javax.annotation.Nullable;
import java.sql.SQLException;


public class InsertValuesToTable extends DatabaseManager {
    public void insertValuesToTable(@Nullable String table, String value) throws SQLException {

        if (table == null) table = "commands";


        String query = "INSERT INTO " + table + " " +
                "(command_name, command_type, command_specification, time, guild_from_id)" +
                " VALUES(" + value + ");";

        System.out.println(query);
        new CommitQuery().commitThisQuery(query);
    }
}

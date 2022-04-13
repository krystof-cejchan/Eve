package db;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCommands extends Database {
    String fullPath;

    public DbCommands(String path) {
        super(path);

        this.fullPath = getFullPath();
    }

    public void executeNewQuery(String query) {
        try (Connection conn = DriverManager.getConnection(fullPath); Statement stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable(@Nullable String table_query) {
        if (table_query == null)
            table_query = "CREATE TABLE \"discord_guilds\" (	\"id\" INTEGER UNIQUE, \"d_id\"	INTEGER NOT NULL UNIQUE,\"d_name\"	TEXT NOT NULL,\"d_prefix\"	TEXT,\"first_noticed\"	TEXT,PRIMARY KEY(\"id\" AUTOINCREMENT));";

        executeNewQuery(table_query);
    }

    /*
     *
     *
     *
     * COMMANDS TO BE ADDED HERE
     *
     *
     *
     *
     */

}

package database_SQLite.analytics;

import database_SQLite.analytics.connection.ConnectToDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager extends ConnectToDatabase {


    @Override
    public void closeConnectionToDabase(Connection conn) throws SQLException {
        super.closeConnectionToDabase(conn);
    }

    @Override
    public Connection connectToDatabase() {
        return super.connectToDatabase();
    }
}

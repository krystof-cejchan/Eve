package cz.krystofcejchan.database_SQLite.analytics;

import cz.krystofcejchan.database_SQLite.analytics.connection.ConnectToDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager extends ConnectToDatabase {


    @Override
    public void closeConnectionToDatabase(Connection conn) throws SQLException {
        super.closeConnectionToDatabase(conn);
    }

    @Override
    public Connection connectToDatabase() {
        return super.connectToDatabase();
    }
}

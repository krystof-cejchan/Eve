package database_SQLite;

import database_SQLite.file_database.connection.ConnectToDatabase;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager extends ConnectToDatabase {


    @Override
    public void closeConnectionToDabase(Connection conn) throws SQLException {
        super.closeConnectionToDabase(conn);
    }

    @Override
    public Connection connectToDatabase() throws SQLException, ClassNotFoundException {
        return super.connectToDatabase();
    }
}

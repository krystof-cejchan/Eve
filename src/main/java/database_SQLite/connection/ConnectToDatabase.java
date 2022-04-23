package database_SQLite.connection;

import database_SQLite.creation.CreationOfDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectToDatabase extends CreationOfDatabase {

    public Connection connectToDatabase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:" + getFullPath());
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
        System.out.println("db ok");
        return null;

    }

    public void closeConnectionToDabase(Connection conn) throws SQLException {
        conn.close();
    }
}

package database_SQLite.analytics.connection;

import database_SQLite.analytics.creation.CreationOfDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects to / closes the database
 *
 * @author krystof-cejchan
 */
public class ConnectToDatabase extends CreationOfDatabase {


    /**
     * Connects to the database and returns {@link Connection}
     * used in classes extending this one
     *
     * @return Connection that is used in classes extending this one
     */
    public Connection connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("db ok " + getFullPath());
            return DriverManager.getConnection("jdbc:sqlite:" + super.getFullPath());
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }

        return null;

    }

    /**
     * Closes a database connection
     *
     * @param conn closes this connection
     * @throws SQLException db errors
     */
    public void closeConnectionToDabase(Connection conn) throws SQLException {
        conn.close();
    }
}

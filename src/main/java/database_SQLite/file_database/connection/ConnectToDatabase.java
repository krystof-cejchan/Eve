package database_SQLite.file_database.connection;

import database_SQLite.file_database.creation.CreationOfDatabase;

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
     * @throws SQLException           for db errors
     * @throws ClassNotFoundException for  Class.forName("org.sqlite.JDBC");
     */
    public Connection connectToDatabase() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.sqlite.JDBC");
            //System.out.println("db ok"+getFullPath());
            return DriverManager.getConnection("jdbc:sqlite:" + getFullPath());
        } catch (Exception e) {
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

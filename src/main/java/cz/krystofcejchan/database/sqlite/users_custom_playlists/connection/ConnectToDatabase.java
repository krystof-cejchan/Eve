package cz.krystofcejchan.database.sqlite.users_custom_playlists.connection;

import cz.krystofcejchan.enums_annotations_exceptions.enums.ExternalFileNames;
import cz.krystofcejchan.link_to_externalfiles.InputStreamHolder;
import org.jetbrains.annotations.Contract;

import javax.annotation.CheckForNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton database class
 */
public class ConnectToDatabase {
    private static ConnectToDatabase singletonInstance = null;
    public static String pathToDatabase;

    static {
        assert InputStreamHolder.fileNameToPathMap != null;
        pathToDatabase = String.valueOf(InputStreamHolder.fileNameToPathMap.get(ExternalFileNames.PUBLIC_PLAYLISTS_DB));
        //    pathToDatabase="D:\\USERS_INPUT_AUDIO\\public_playlists.db";
    }

    private Connection sqlConnection;

    private ConnectToDatabase() {
    }

    public static ConnectToDatabase getInstance() {
        if (singletonInstance == null)
            singletonInstance = new ConnectToDatabase();
        return singletonInstance;
    }

    @CheckForNull
    public Connection connectToDatabase() {
        if (sqlConnection != null) return sqlConnection;
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("db ok " + pathToDatabase);

            setSqlConnection(DriverManager.getConnection("jdbc:sqlite:" + pathToDatabase));

            return sqlConnection;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }

        return null;
    }

    private Connection getSqlConnection() {
        return sqlConnection;
    }

    @Contract(mutates = "this")
    private void setSqlConnection(Connection sqlConnection) {
        if (this.sqlConnection == null)
            this.sqlConnection = sqlConnection;
    }
}

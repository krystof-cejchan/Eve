package database_SQLite.analytics.queries;

import database_SQLite.analytics.DatabaseManager;
import org.sqlite.SQLiteException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommitQuery extends DatabaseManager {


    /**
     * Commits a query and closes the database connection
     *
     * @param query SQL query
     * @throws SQLException if SQL query fails
     */
    protected void commitThisQuery(String query) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = super.connectToDatabase().prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assert statement != null;

        try {
            statement.execute();
            super.connectToDatabase().close();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            if (!connectToDatabase().isClosed())
                connectToDatabase().close();
            statement.execute();

        }
    }
}

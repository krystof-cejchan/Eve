package cz.krystofcejchan.database_SQLite.file_database.queries;

import cz.krystofcejchan.database_SQLite.file_database.DatabaseManager;

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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        assert statement != null;
        statement.execute();
        try {
            super.connectToDatabase().close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

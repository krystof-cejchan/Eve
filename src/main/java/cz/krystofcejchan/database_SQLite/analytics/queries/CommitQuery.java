package cz.krystofcejchan.database_SQLite.analytics.queries;

import cz.krystofcejchan.database_SQLite.analytics.DatabaseManager;
import cz.krystofcejchan.database_SQLite.analytics.connection.ConnectToDatabase;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommitQuery extends DatabaseManager {


    /**
     * Commits a query and closes the database connection
     *
     * @param query SQL query
     */
    protected void commitThisQuery(String query) {
        PreparedStatement statement;
        try {
          //  statement = ConnectToDatabase.getInstance().connectToDatabase().prepareStatement(query);
            statement = new ConnectToDatabase().connectToDatabase().prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//
//        assert statement != null;
//
//        try {
//            statement.execute();
//            super.connectToDatabase().close();
//        } catch (SQLiteException e) {
//            e.printStackTrace();
//        } finally {
//            if (!connectToDatabase().isClosed())
//                connectToDatabase().close();
//            statement.execute();
//
//        }
    }
}

package database_SQLite.analytics.queries;

import database_SQLite.analytics.DatabaseManager;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class RetrieveData extends DatabaseManager {


    public void printOutAll(@Nullable String table) throws SQLException {
        if (table == null)
            table = "commands";
        String query = "SELECT * FROM " + table + ";";


        PreparedStatement prepared = super.connectToDatabase().prepareStatement(query);
        ResultSet resultSet = prepared.executeQuery();
        ResultSetMetaData metadata = resultSet.getMetaData();
        int columnCount = metadata.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(resultSet.getString(i));
            }

            resultSet.close();
            super.connectToDatabase().close();
            System.out.println("---END---");

        }
    }

    public ArrayList<String> getCertainDataFromDb(@Nullable String table, String[] columns) throws
            SQLException {
        if (table == null)
            table = "commands";
        String query = "SELECT * FROM " + table + ";";


        PreparedStatement prepared = super.connectToDatabase().prepareStatement(query);
        ResultSet resultSet = prepared.executeQuery();
        ArrayList<String> resultsDB = new ArrayList<>();
        while (resultSet.next()) {
            for (String column : columns) {
                resultsDB.add(resultSet.getString(column));
            }
        }
        resultSet.close();
        super.connectToDatabase().close();
        System.out.println("data retrieved");
        return resultsDB;

    }
}
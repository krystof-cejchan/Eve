package database_SQLite.file_database.queries;

import database_SQLite.file_database.DatabaseManager;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RetrieveData extends DatabaseManager {


    public void printOutAll(@Nullable String table) throws SQLException, ClassNotFoundException {
        if (table == null)
            table = "audio_file_locations";
        String query = "SELECT * FROM " + table + ";";


        PreparedStatement prepared = super.connectToDatabase().prepareStatement(query);
        ResultSet resultSet = prepared.executeQuery();
        ArrayList<String> resultsDB = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String path = resultSet.getString("path");
            System.out.println(id + " " + path);
        }
        resultSet.close();
        super.closeConnectionToDabase(connectToDatabase());
        System.out.println("---END---");

    }

    public ArrayList<String> getCertainDataFromDb(@Nullable String table, String[] columns) throws SQLException, ClassNotFoundException {
        if (table == null)
            table = "audio_file_locations";
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
        super.closeConnectionToDabase(connectToDatabase());
        System.out.println("data retrieved");
        return resultsDB;

    }
}
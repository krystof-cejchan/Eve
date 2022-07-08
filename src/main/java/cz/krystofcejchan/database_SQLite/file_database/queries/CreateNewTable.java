package cz.krystofcejchan.database_SQLite.file_database.queries;

import cz.krystofcejchan.database_SQLite.file_database.DatabaseManager;
import cz.krystofcejchan.main.pre_setup.IPreSetUp;

import java.sql.SQLException;

public class CreateNewTable extends DatabaseManager implements IPreSetUp {


    /*
            CREATE TABLE "audio_file_locations" (
            "id"	INTEGER NOT NULL UNIQUE,
            "path"	TEXT NOT NULL UNIQUE,
            PRIMARY KEY("id" AUTOINCREMENT)
        );
             */
    public void createNewTable() throws SQLException {

        String query = """
                CREATE TABLE IF NOT EXISTS "audio_file_locations" (
                \t"id"\tINTEGER NOT NULL UNIQUE,
                \t"path"\tTEXT NOT NULL UNIQUE,
                \tPRIMARY KEY("id" AUTOINCREMENT)
                );""";


        new CommitQuery().commitThisQuery(query);
    }

    @Override
    public void GetReady() {
        try {
            createNewTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package database_SQLite.queries;

import database_SQLite.DatabaseManager;

import java.sql.SQLException;

public class CreateNewTable extends DatabaseManager {
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

}

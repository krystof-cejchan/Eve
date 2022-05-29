package database_SQLite.analytics.queries;

import database_SQLite.analytics.DatabaseManager;
import main.pre_setup.IPreSetUp;

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
                CREATE TABLE IF NOT EXISTS 'commands' (
                \t'id'\tINTEGER NOT NULL UNIQUE,
                \t'command_type'\tTEXT NOT NULL DEFAULT 'n/a',
                \t'command_specification'\tTEXT NOT NULL DEFAULT 'n/a',
                \t'time'\tTEXT DEFAULT 'n/a',
                \t'guild_from_id'\tINTEGER DEFAULT 0,
                \tPRIMARY KEY('id' AUTOINCREMENT)
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

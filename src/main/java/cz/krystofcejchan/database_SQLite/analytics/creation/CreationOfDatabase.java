package cz.krystofcejchan.database_SQLite.analytics.creation;

import java.io.File;
import java.io.IOException;

public class CreationOfDatabase {
    private final String path;

    public CreationOfDatabase(/*@Nullable String path*/) {
        // this.path = Objects.requireNonNullElse(path, "src/main/java/database_SQLite/file_database.db");
        this.path = "src/main/java/database_SQLite/analytics/analyticdata.db";
        System.out.println(getFullPath());
    }

    public String getPath() {
        return path;
    }

    public String getFullPath() {
        return new File(getPath()).getAbsolutePath();
    }

    protected void checkIfDatabaseExistsAndCreateNewOneIfNeeded() throws IOException {
        File dbFile = new File(path);
        if (!dbFile.exists())
            dbFile.createNewFile();

    }
    /*CREATE TABLE "commands" (
	"id"	INTEGER NOT NULL UNIQUE,
	"command_name"	TEXT,
	"command_type"	TEXT DEFAULT 'n/a',
	"command_specification"	TEXT DEFAULT 'n/a',
	"time"	TEXT DEFAULT 'n/a',
	"guild_from_id"	INTEGER DEFAULT 0,
	PRIMARY KEY("id" AUTOINCREMENT)
)*/
}

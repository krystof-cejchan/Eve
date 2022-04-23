package database_SQLite.creation;

import java.io.File;
import java.io.IOException;

public class CreationOfDatabase {
    private final String path;

    public CreationOfDatabase() {
        this.path = "src/main/java/database_SQLite/file_database.db";
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
}

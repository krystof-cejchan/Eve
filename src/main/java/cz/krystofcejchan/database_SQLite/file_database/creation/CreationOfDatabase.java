package cz.krystofcejchan.database_SQLite.file_database.creation;

import java.io.File;
import java.io.IOException;

public class CreationOfDatabase {
    private final String path;

    public CreationOfDatabase(/*@Nullable String path*/) {
        // this.path = Objects.requireNonNullElse(path, "src/main/java/database_SQLite/file_database.db");
        this.path = "src/main/java/cz/krystofcejchan/database_SQLite/file_database/file_database.db";
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
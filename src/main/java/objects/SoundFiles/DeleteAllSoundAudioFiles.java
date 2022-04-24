package objects.SoundFiles;

import database_SQLite.queries.RemoveRecord;
import database_SQLite.queries.RetrieveData;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeleteAllSoundAudioFiles {
    public static void deleteAllSoundFiles() throws SQLException, ClassNotFoundException {
        ArrayList<String> paths = new ArrayList<>(new RetrieveData().getCertainDataFromDb(null, new String[]{"path"}));
        for (String path : paths) {
            File soundFile = new File(path);
            if (soundFile.exists() && soundFile.isFile()) {
                if (soundFile.delete()) {
                    System.out.println(soundFile.getName() + " deleted");
                    new RemoveRecord().removeRecordFromDatabasebyRecord(null, path);
                } else
                    System.out.println(soundFile.getName() + " failed to be deleted");

            }
        }

    }
}

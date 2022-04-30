package objects.sound_files;

import database_SQLite.file_database.queries.RemoveRecord;
import database_SQLite.file_database.queries.RetrieveData;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Deleting all sound files and its records stored in database
 *
 * @author krystof-cejchan
 */
public class DeleteSoundAudioFilesFromSystemAndDatabase {
    /**
     * Goes through all records from database and uses them as path to file to be deleted, futhermore deletes the record from database
     *
     * @throws SQLException           SQl query or connectivity error{@link SQLException}
     * @throws ClassNotFoundException Class not found{@link ClassCastException}
     */
    public static void deleteAllSoundFilesOnebyOneFromSystemAndDatabase() throws SQLException, ClassNotFoundException {
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

    public static void delete1SoundFileFromSystemAndDatabase(String path) throws SQLException {
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

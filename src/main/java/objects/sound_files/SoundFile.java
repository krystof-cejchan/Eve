package objects.sound_files;

import enums_annotations_exceptions.enums.OS;
import utility_class.GlobalValues;

import java.io.IOException;
import java.nio.file.*;

public class SoundFile {
    private static String title;

    public SoundFile(String title) {

        SoundFile.title = title;


    }

    public static String getTitle() {
        return title;
    }

    static public void setTitle(String title) {
        SoundFile.title = title;
    }

    public static String getWholePath() throws IOException {
        return GlobalValues.operatingSystem == OS.WINDOWS
                ? Directories.getCompletePathToSoundFiles() + "\\" + getTitle() + ".wav"
                : Directories.getCompletePathToSoundFiles() + "/" + getTitle() + ".wav";
    }

    static public class Directories {
        public static FileStore getTheFirstFireStore() throws IOException {
            for (Path root : FileSystems.getDefault().getRootDirectories()) {
                if (!Files.getFileStore(root).isReadOnly() && Files.isWritable(root))
                    return Files.getFileStore(root);
            }
            throw new IOException("No disk was found");

        }

        public static String getTheFirstDiskLetter() throws IOException {
            return getTheFirstFireStore().toString().replaceAll("\\(", "").replaceAll("\\)", "");
        }

        /**
         * returns a complete path to folder where sound files are stored<hr>
         * <b>•if os is windows</b> then a folder on the first writeable/readable disk will be created and all file sounds will be stored there<br>
         * <b>•linux</b> path will look like this: /home/<i>user's name</i>/Documents
         *
         * @return Path to a folder
         * @throws IOException if disk was not found
         */
        public static Path getCompletePathToSoundFiles() throws IOException {
            return GlobalValues.operatingSystem != OS.LINUX ?
                    Paths.get(getTheFirstDiskLetter().replace("VirtualDisk ", "") +
                            "\\USERS_INPUT_AUDIO")
                    : Path.of("/home/" + System.getProperty("user.name") + "/Documents");
        }
    }
}

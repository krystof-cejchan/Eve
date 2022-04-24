package objects.sound_files;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

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
        return Directories.getthePath() + "\\" + getTitle() + ".wav";
    }

    static public class Directories {
        public static ArrayList<FileStore> getAllAvaiableFireStores() {
            ArrayList<FileStore> stores = new ArrayList<>();
            FileSystems.getDefault().getRootDirectories().forEach(root -> {
                try {
                    stores.add(Files.getFileStore(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return stores;
        }

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

        public static Path getthePath() throws IOException {
            return Paths.get(getTheFirstDiskLetter() + "\\USERS_INPUT_AUDIO");
        }


    }
}

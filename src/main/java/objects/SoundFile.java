package objects;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static String getWholePath() {
        return "H:\\" + getTitle() + ".wav";
    }

    static public class Directories {
        public static ArrayList<FileStore> getAllAvaiableFireStores() throws IOException {
            ArrayList<FileStore> stores = new ArrayList<>();
            for (Path root : FileSystems.getDefault().getRootDirectories()) {
                stores.add(Files.getFileStore(root));
            }
            return stores;
        }

        public static FileStore getTheFirstFireStore() throws IOException {
            for (Path root : FileSystems.getDefault().getRootDirectories()) {
                return (Files.getFileStore(root));
            }
            throw new IOException("No disk was found");

        }

    }
}

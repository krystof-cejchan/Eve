package cz.krystofcejchan.link_to_externalfiles;

import cz.krystofcejchan.enums_annotations_exceptions.enums.ExternalFileNames;
import cz.krystofcejchan.objects.sound_files.SoundFile;

import javax.annotation.CheckForNull;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class InputStreamHolder {

    private final InputStream inputStream;
    private final ExternalFileNames name;

    @CheckForNull
    public static HashMap<ExternalFileNames, Path> fileNameToPathMap = new HashMap<>();

    public InputStreamHolder(InputStream inputStream, ExternalFileNames name, String suffix) {
        this.inputStream = inputStream;
        this.name = name;

        assert fileNameToPathMap != null;
        fileNameToPathMap.computeIfAbsent(name, v -> {
            try {
                return Path.of(SoundFile.Directories.getCompletePathToSoundFiles() + "/" + name + suffix);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }


    public InputStream getInputStream() {
        return inputStream;
    }

    public ExternalFileNames getName() {
        return name;
    }

    public void transformInputStreamToLocalFile(ExternalFileNames fileName) throws IOException {
        assert fileNameToPathMap != null;
        Files.copy(getInputStream(), fileNameToPathMap.get(fileName), StandardCopyOption.REPLACE_EXISTING);
    }

}
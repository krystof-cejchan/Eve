package cz.krystofcejchan.link_to_externalfiles;

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
    private final ExternalFileNamesE name;

    @CheckForNull
    public static HashMap<ExternalFileNamesE, Path> fileNameToPathMap = new HashMap<>();

    public InputStreamHolder(InputStream inputStream, ExternalFileNamesE name, String suffix) {
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

    public ExternalFileNamesE getName() {
        return name;
    }

    public void transformInputStreamToLocalFile(ExternalFileNamesE fileName) throws IOException {
        assert fileNameToPathMap != null;
        System.out.println(fileNameToPathMap.get(fileName));
        //  if (fileName.toString().contains("DB") ^ Files.exists(fileNameToPathMap.get(fileName)))
        if (!Files.exists(fileNameToPathMap.get(fileName)))
            Files.copy(getInputStream(), fileNameToPathMap.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        else {
            if (!fileName.toString().contains("DB"))
                Files.copy(getInputStream(), fileNameToPathMap.get(fileName), StandardCopyOption.REPLACE_EXISTING);
            else
                System.out.println(fileName.name() + " ALREADY EXISTS AND IS NOT GOING TO BE CREATED AGAIN");
        }
    }

}


package main.onstart;

import objects.sound_files.SoundFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;

public class AreSoundFilesReady implements IOnStart {
    @Override
    public void doYourPart() throws Exception {
        if (!Files.exists(SoundFile.Directories.getthePath()))
            throw new FileNotFoundException("file not found");
    }

}

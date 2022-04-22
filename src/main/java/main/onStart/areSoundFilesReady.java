package main.onStart;

import objects.SoundFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;

public class areSoundFilesReady implements IOnStart{
    @Override
    public void doYourPart() throws Exception {
        if (!Files.exists(SoundFile.Directories.getthePath()))
            throw new FileNotFoundException("file not found");
    }

}

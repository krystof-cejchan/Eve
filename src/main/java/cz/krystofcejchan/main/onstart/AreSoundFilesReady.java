package cz.krystofcejchan.main.onstart;

import cz.krystofcejchan.objects.sound_files.SoundFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;

public class AreSoundFilesReady implements IOnStart {
    @Override
    public void doYourPart() throws Exception {
        if (!Files.exists(SoundFile.Directories.getCompletePathToSoundFiles()))
            throw new FileNotFoundException("file not found");
    }

}

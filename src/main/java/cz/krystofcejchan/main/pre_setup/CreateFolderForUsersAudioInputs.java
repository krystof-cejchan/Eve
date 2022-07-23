package cz.krystofcejchan.main.pre_setup;


import cz.krystofcejchan.objects.sound_files.SoundFile;

import java.io.IOException;
import java.nio.file.Files;

public class CreateFolderForUsersAudioInputs implements IPreSetUp {
    @Override
    public void getReady() {
        try {
            if (!Files.exists(SoundFile.Directories.getCompletePathToSoundFiles())) Files.createDirectory(SoundFile.Directories.getCompletePathToSoundFiles());
            else System.out.println("already exists");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

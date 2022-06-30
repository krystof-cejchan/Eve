package main.pre_setup;


import objects.sound_files.SoundFile.Directories;

import java.io.IOException;
import java.nio.file.Files;

public class CreateFolderForUsersAudioInputs implements IPreSetUp {
    @Override
    public void GetReady() {
        try {
            if (!Files.exists(Directories.getCompletePathToSoundFiles())) Files.createDirectory(Directories.getCompletePathToSoundFiles());
            else System.out.println("already exists");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

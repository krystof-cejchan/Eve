package main.pre_SetUp;


import objects.sound_files.SoundFile.Directories;

import java.io.IOException;
import java.nio.file.Files;

public class CreateFolderForUsersAudioInputs implements IPreSetUp {
    @Override
    public void GetReady() {
        try {
            if (!Files.exists(Directories.getthePath())) Files.createDirectory(Directories.getthePath());
            else System.out.println("already exists");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

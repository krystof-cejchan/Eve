package main.pre_SetUp

import objects.SoundFile.Directories
import java.io.IOException
import java.nio.file.Files

class CreateFolderForUsersAudioInputs : IPreSetUp {
    override fun GetReady() {
        try {
            if (!Files.exists(Directories.getthePath())) Files.createDirectory(Directories.getthePath()) else println("already exists")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
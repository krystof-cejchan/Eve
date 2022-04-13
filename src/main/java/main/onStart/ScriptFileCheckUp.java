package main.onStart;

import enums.ScriptFilesLocation;
import objects.ScriptFiles;

import java.io.File;
import java.io.FileNotFoundException;

public class ScriptFileCheckUp extends ScriptFiles implements IOnStart {

    public ScriptFileCheckUp(File translator, File soundFile2Text, File dbPath) {
        super(translator, soundFile2Text, dbPath);
    }

    public ScriptFileCheckUp(String translatorPath, String soundFile2TextPath, String dbPath_Path) {
        super(translatorPath, soundFile2TextPath, dbPath_Path);
    }

    public ScriptFileCheckUp(ScriptFilesLocation scriptFilesLocation) {
        super(scriptFilesLocation);
    }

    @Override
    public void doYourPart() throws Exception {
        if (!super.areAllFilesAvailable(new File[]{getTranslator(), getSoundFile2Text()})) {
            throw new FileNotFoundException("a File does not exist");
        }
    }

}

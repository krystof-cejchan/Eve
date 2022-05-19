package main.onStart;

import enums_and_annotations.enums.ScriptFilesLocation;
import objects.ScriptFiles;

import java.io.File;
import java.io.FileNotFoundException;

public class ScriptFileCheckUp extends ScriptFiles implements IOnStart {

    public ScriptFileCheckUp(File translator, File soundFile2Text, File dbPath) {
        super(translator, soundFile2Text, dbPath);
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

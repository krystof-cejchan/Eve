package main.onstart;

import enums_annotations_exceptions.enums.ScriptFilesLocation;
import objects.ScriptFiles;

import java.io.File;
import java.io.FileNotFoundException;

public class ScriptFileCheckUp extends ScriptFiles implements IOnStart {

    public ScriptFileCheckUp(File translator, File soundFile2Text, File dbPath, File ytSearch) {
        super(translator, soundFile2Text, ytSearch, dbPath);
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

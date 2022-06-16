package objects;


import enums_annotations_exceptions.enums.ScriptFilesLocation;

import java.io.File;

public class ScriptFiles extends ScriptPathPointer {
    private final File translator;
    private final File soundFile2Text;
    private final File ytSearch;

    private final File[] allFilesAvailable;


    public ScriptFiles(File translator, File soundFile2Text, File ytSearch, File dbPath) {
        this.translator = translator;
        this.soundFile2Text = soundFile2Text;
        this.ytSearch = ytSearch;

        allFilesAvailable = new File[]{translator, soundFile2Text, dbPath};
    }

  /*  public ScriptFiles(String translatorPath, String soundFile2TextPath, String dbPath_Path) {
        this.translator = new File(translatorPath);
        this.soundFile2Text = new File(soundFile2TextPath);

        allFilesAvailable = new File[]{translator, soundFile2Text};
    }
*/

    /**
     * constructor without any params
     * all local variables are given data from {@link ScriptPathPointer}
     */
    public ScriptFiles(ScriptFilesLocation scriptFilesLocation) {

        if (scriptFilesLocation == ScriptFilesLocation.fromWEB) {
            this.translator = new File(ScriptPathPointer.translator);
            this.soundFile2Text = new File(ScriptPathPointer.soundFile2Text);
            this.ytSearch = new File("./src/main/java/external_files/py_scripts/ytsearch.py");

        } else {
            this.translator = new File("./src/main/java/external_files/py_scripts/translator.py");
            this.soundFile2Text = new File("./src/main/java/external_files/py_scripts/soundfiletotext.py");
            this.ytSearch = new File("src/main/java/external_files/py_scripts/ytsearch.py");


        }


        allFilesAvailable = new File[]{translator, soundFile2Text, ytSearch};
    }

    public File getTranslator() {
        return translator;
    }

    public File getSoundFile2Text() {
        return soundFile2Text;
    }

    public File getYtSearch() {
        return ytSearch;
    }


    protected boolean areAllFilesAvailable(File[] files) {
        for (File file : files) {
            if (!isFileAvailable(file)) return false;
        }
        return true;
    }

    private boolean isFileAvailable(File file) {
        return file.exists() && file.isFile();

    }


}




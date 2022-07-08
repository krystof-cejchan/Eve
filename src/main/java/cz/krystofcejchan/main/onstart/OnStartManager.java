package cz.krystofcejchan.main.onstart;

import java.util.ArrayList;

public class OnStartManager {
    private final ArrayList<IOnStart> listOf_onStartClasses = new ArrayList<>();

    public OnStartManager() {

        // listOf_onStartClasses.add(new Database_class_onStart());
        listOf_onStartClasses.add(new AreSoundFilesReady());
        listOf_onStartClasses.add(new CheckPyPaths());
    }

    public ArrayList<IOnStart> getListOf_onStartClasses() {
        return listOf_onStartClasses;
    }


}

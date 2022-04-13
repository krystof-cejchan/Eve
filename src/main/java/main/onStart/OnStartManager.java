package main.onStart;

import enums.ScriptFilesLocation;

import java.util.ArrayList;

public class OnStartManager {
    private final ArrayList<IOnStart> listOf_onStartClasses = new ArrayList<>();

    public OnStartManager() {

       // listOf_onStartClasses.add(new Database_class_onStart());
        listOf_onStartClasses.add(new ScriptFileCheckUp(ScriptFilesLocation.fromLOCAL));
    }

    public ArrayList<IOnStart> getListOf_onStartClasses() {
        return listOf_onStartClasses;
    }


}

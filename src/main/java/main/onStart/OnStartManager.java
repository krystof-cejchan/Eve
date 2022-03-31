package main.onStart;

import java.util.ArrayList;

public class OnStartManager {
    private ArrayList<IOnStart> listOf_onStartClasses = new ArrayList<>();

    public OnStartManager() {

        listOf_onStartClasses.add(new Database_class_onStart());
        //listOf_onStartClasses.add(new )
    }

    public ArrayList<IOnStart> getListOf_onStartClasses() {
        return listOf_onStartClasses;
    }


}

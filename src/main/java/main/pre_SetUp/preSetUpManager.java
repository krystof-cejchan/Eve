package main.pre_SetUp;

import database_SQLite.file_database.queries.CreateNewTable;

import java.util.ArrayList;

public class preSetUpManager {
    private final ArrayList<IPreSetUp> preSetUps = new ArrayList<>();

    public preSetUpManager() {
        preSetUps.add(new CreateFolderForUsersAudioInputs());
        preSetUps.add(new CreateNewTable());
    }

    public ArrayList<IPreSetUp> getPreSetUps() {
        return preSetUps;
    }
}

package cz.krystofcejchan.main.pre_setup;

import java.util.ArrayList;

public class preSetUpManager {
    private final ArrayList<IPreSetUp> preSetUps = new ArrayList<>();

    public preSetUpManager() {
        preSetUps.add(new CreateFolderForUsersAudioInputs());
        // preSetUps.add(new CreateNewTable());
    }

    public ArrayList<IPreSetUp> getPreSetUps() {
        return preSetUps;
    }
}

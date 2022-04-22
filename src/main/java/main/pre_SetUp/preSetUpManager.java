package main.pre_SetUp;

import java.util.ArrayList;

public class preSetUpManager {
    private final ArrayList<IPreSetUp> preSetUps = new ArrayList<>();

    public preSetUpManager() {
        preSetUps.add(new CreateFolder_forUsersAudioInputs());
    }

    public ArrayList<IPreSetUp> getPreSetUps() {
        return preSetUps;
    }
}

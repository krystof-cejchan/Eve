package main.after_startup;

import java.util.ArrayList;

public class AfterStartUpManager {
    private final ArrayList<IAfterStartUp> iAfterStartUpArrayList = new ArrayList<>();

    public AfterStartUpManager() {
        addToArray(new AddingSlashCommandsToGuilds());
       // addToArray(new main.after_startup.DeletingAllSlashCommandsFromAllGuilds());

    }

    private void addToArray(IAfterStartUp iAfterStartUp) {
        if (iAfterStartUpArrayList.stream().noneMatch(potMatch -> potMatch.equals(iAfterStartUp)))
            iAfterStartUpArrayList.add(iAfterStartUp);
    }

    public ArrayList<IAfterStartUp> getiAfterStartUpArrayList() {
        return iAfterStartUpArrayList;
    }
}

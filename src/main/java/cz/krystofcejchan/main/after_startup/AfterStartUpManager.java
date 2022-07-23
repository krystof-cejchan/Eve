package cz.krystofcejchan.main.after_startup;

import java.util.ArrayList;

public class AfterStartUpManager {
    private final ArrayList<IAfterStartUp> iAfterStartUpArrayList = new ArrayList<>();

    public AfterStartUpManager() {
        addToArray(new AddingSlashCommandsToGuilds());
        // addToArray(new main.after_startup.DeletingAllSlashCommandsFromAllGuilds());
        addToArray(new CreateNewExternalFilesLocally());

        addToArray(new DatabaseConnectionEstablishment());

    }

    private void addToArray(IAfterStartUp iAfterStartUp) {
        if (iAfterStartUpArrayList.stream().noneMatch(potMatch -> potMatch.equals(iAfterStartUp)))
            iAfterStartUpArrayList.add(iAfterStartUp);
    }

    public ArrayList<IAfterStartUp> getIAfterStartUpArrayList() {
        return iAfterStartUpArrayList;
    }
}

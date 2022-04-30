package commands.admin;

import java.util.ArrayList;

public class AdminCommandManager {
    private final ArrayList<IAdmin> iAdminCommands = new ArrayList<>();

    public AdminCommandManager() {
        addIAdminCommands(new DeleteAllAudioFiles());
    }

    public ArrayList<IAdmin> getiAdminCommands() {
        return iAdminCommands;
    }

    public void addIAdminCommands(IAdmin iAdminCommand) {
        if (iAdminCommands.stream().noneMatch(potentialMatch -> potentialMatch.equals(iAdminCommand))) {
            this.iAdminCommands.add(iAdminCommand);
        }
    }

}

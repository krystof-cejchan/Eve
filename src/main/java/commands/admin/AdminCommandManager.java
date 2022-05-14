package commands.admin;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Manager of admins' commands
 */
public class AdminCommandManager {
    private final ArrayList<IAdmin> iAdminCommands = new ArrayList<>();

    public AdminCommandManager() {
        addIAdminCommands(new DeleteAllAudioFiles());
        addIAdminCommands(new HardShutDown());
    }

    public void addIAdminCommands(IAdmin iAdminCommand) {
        if (iAdminCommands.stream().noneMatch(potentialMatch -> potentialMatch.equals(iAdminCommand))) {
            this.iAdminCommands.add(iAdminCommand);
        }
    }

    /**
     * gets a command from iAdminCommands
     *
     * @param input user's text input
     * @return IAdmin command that matches user's input
     */
    public IAdmin getCommand(String input) {
        for (IAdmin comm : iAdminCommands) {
            if (Arrays.asList(comm.getTags()).contains(input.replace(IAdmin.adminPrefix(), "")))
                return comm;
        }
        return null;
    }

}

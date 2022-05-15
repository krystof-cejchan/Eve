package commands.commands_slash;

import java.util.ArrayList;

public class SlashCommandManager {
    private final ArrayList<ISlashCommands> commands = new ArrayList<>();

    public SlashCommandManager() {
        addNewCommand(new __test__());
    }

    public  ArrayList<ISlashCommands> getAllCommands() {
        return commands;
    }

    private void addNewCommand(ISlashCommands Icmd) {

        if (commands.stream().noneMatch(matched -> matched.getName().equals(Icmd.getName()))) {
            commands.add(Icmd);
        }

    }
}

package commands.commands_slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;

public class SlashCommandManager {
    private final ArrayList<ISlashCommands> commands = new ArrayList<>();

    public SlashCommandManager() {
        //addNewCommand(new __test__());
        addNewCommand(new PlaySong());
        addNewCommand(new PlayQueue());
        addNewCommand(new Join());
        addNewCommand(new Hey());
        addNewCommand(new Skip_full());
        addNewCommand(new Skip_toSongbyTitle());


        commands.sort((commands1, commands2) -> commands1.getName().compareToIgnoreCase(commands2.getName()));
    }

    public ArrayList<ISlashCommands> getAllCommands() {
        return commands;
    }

    private void addNewCommand(ISlashCommands Icmd) {
        boolean exists = commands.stream().anyMatch(match -> match.equals(Icmd));

        if (!exists) {
            commands.add(Icmd);
            System.out.println(Icmd);
        }

    }

    /**
     * returns {@link ISlashCommands} which name is the same as the slash command name triggered by a user
     *
     * @param event {@link SlashCommandInteractionEvent}
     * @return ISlashCommands that matches the name of the command
     */
    public ISlashCommands getSlashCommand(SlashCommandInteractionEvent event) {
        for (ISlashCommands cmd : getAllCommands()) {
            if (cmd.getName().equalsIgnoreCase(event.getName())) return cmd;
        }
        return null;
    }

}

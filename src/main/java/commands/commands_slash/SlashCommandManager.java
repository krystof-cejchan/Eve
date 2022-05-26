package commands.commands_slash;

import commands.commands_slash.volume.VolumeCustomSLASH;
import commands.commands_slash.volume.VolumeDownSlash;
import commands.commands_slash.volume.VolumeUpSlash;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;

public class SlashCommandManager {
    private final ArrayList<ISlashCommands> commands = new ArrayList<>();

    public SlashCommandManager() {
        addNewCommand(new PlaySongSlash());
        addNewCommand(new PlayQueueSlash());
        addNewCommand(new JoinSlash());
        addNewCommand(new HeySlash());
        addNewCommand(new Skip_fullSlash());
        addNewCommand(new Skip_toSongbyTitleSlash());
        addNewCommand(new QueueSlash());
        addNewCommand(new VolumeCustomSLASH());
        addNewCommand(new VolumeUpSlash());
        addNewCommand(new VolumeDownSlash());

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

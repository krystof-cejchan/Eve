package main.after_startup;


import commands.commands_slash.ISlashCommands;
import commands.commands_slash.SlashCommandManager;
import main.Main;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Objects;

public class AddingSlashCommandsToGuilds implements IAfterStartUp {
    private void addSlashCommandsToTheGuilds() {
        SlashCommandManager slashCommandManager = new SlashCommandManager();
        for (Guild guild : Main.publicJDA.getGuilds()) {

            if (library_class.GlobalValues.RESET_SLASH_COMMANDS)
                guild.updateCommands().queue();

            for (ISlashCommands iSlashCommands : slashCommandManager.getAllCommands()) {


               /* if (iSlashCommands.getClass().isAnnotationPresent(Slash.class) && iSlashCommands.getClass().getAnnotation(Slash.class) != null)
                    continue;*/

                //if (!iSlashCommands.getClass().getAnnotation(Slash.class).active()) continue;

                switch (iSlashCommands.takesArguments()) {
                    case NONE -> guild.upsertCommand(iSlashCommands.getName().toLowerCase(), iSlashCommands.getDescription())
                            .queue();

                    case ONE -> guild.upsertCommand(iSlashCommands.getName().toLowerCase(), iSlashCommands.getDescription())
                            .addOptions(Objects.requireNonNull(iSlashCommands.getOptionData()).get(0)).queue();

                    case MULTIPLE -> guild.upsertCommand(iSlashCommands.getName().toLowerCase(), iSlashCommands
                            .getDescription()).addOptions(Objects.requireNonNull(iSlashCommands.getOptionData())).queue();

                    default -> throw new NullPointerException("slash command takes unknown number of arguments/options");

                }


            }

        }
    }

    public static void addSlashCommandsToSpecificGuild(Guild guild) {
        for (ISlashCommands iSlashCommands : new SlashCommandManager().getAllCommands()) {
            switch (iSlashCommands.takesArguments()) {
                case NONE -> guild.upsertCommand(iSlashCommands.getName().toLowerCase(), iSlashCommands.getDescription())
                        .queue();

                case ONE -> guild.upsertCommand(iSlashCommands.getName().toLowerCase(), iSlashCommands.getDescription())
                        .addOptions(Objects.requireNonNull(iSlashCommands.getOptionData()).get(0)).queue();

                case MULTIPLE -> guild.upsertCommand(iSlashCommands.getName().toLowerCase(), iSlashCommands
                        .getDescription()).addOptions(Objects.requireNonNull(iSlashCommands.getOptionData())).queue();

                default -> throw new NullPointerException("slash command takes unknown number of arguments/options");
            }
        }
    }

    @Override
    public void doAfterStartUp() {
        addSlashCommandsToTheGuilds();
    }
}

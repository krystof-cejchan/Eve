package main.after_startup;


import commands.commands_slash.ISlashCommands;
import commands.commands_slash.SlashCommandManager;
import main.StartUp;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Objects;

public class AddingSlashCommandsToGuilds implements IAfterStartUp {
    protected void addSlashCommandsToTheGuilds() {
        SlashCommandManager slashCommandManager = new SlashCommandManager();
        for (Guild guild : StartUp.publicJDA.getGuilds()) {

            System.out.println(guild);
            if (library_class.GlobalValues.resetSlashCommands)
                guild.updateCommands().queue();

            for (ISlashCommands iSlashCommands : slashCommandManager.getAllCommands()) {
                System.out.println(slashCommandManager.getAllCommands());

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

    @Override
    public void doAfterStartUp() {
        addSlashCommandsToTheGuilds();
    }
}

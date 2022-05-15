package main.onStart;


import commands.commands_slash.ISlashCommands;
import commands.commands_slash.SlashCommandManager;
import main.StartUp;
import net.dv8tion.jda.api.entities.Guild;

public class addingSlashCommandsToGuilds {
    public static void addSlashCommandsToTheGuilds() {
        SlashCommandManager slashCommandManager = new SlashCommandManager();
        for (Guild guild : StartUp.publicJDA.getGuilds()) {
            System.out.println(guild);
            for (ISlashCommands iSlashCommands : slashCommandManager.getAllCommands()) {
                System.out.println(slashCommandManager.getAllCommands());

                if (!iSlashCommands.takesArguments())
                    guild.upsertCommand(iSlashCommands.getName().toLowerCase(), iSlashCommands.getDescription()).queue();

                else {
                    guild.upsertCommand(iSlashCommands.getName(), iSlashCommands.getDescription())
                            .addOptions(iSlashCommands.getOptionData()).queue();

                }

            }
        }
    }
}

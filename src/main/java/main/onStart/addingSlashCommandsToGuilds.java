package main.onStart;


import commands.commands_slash.ISlashCommands;
import commands.commands_slash.SlashCommandManager;
import main.StartUp;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Locale;

public class addingSlashCommandsToGuilds {
    public static void addSlashCommandsToTheGuilds() {
        SlashCommandManager slashCommandManager = new SlashCommandManager();
        for (Guild guild : StartUp.publicJDA.getGuilds()) {
            System.out.println(guild);
            for (ISlashCommands iSlashCommands : slashCommandManager.getAllCommands()) {
                System.out.println(slashCommandManager.getAllCommands());
                guild.upsertCommand(iSlashCommands.getName().toLowerCase(Locale.ROOT), iSlashCommands.getDescription()).queue();

            }
        }
    }
}

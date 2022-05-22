package main.afterStartUp;


import commands.commands_slash.ISlashCommands;
import commands.commands_slash.SlashCommandManager;
import main.StartUp;
import net.dv8tion.jda.api.entities.Guild;

public class AddingSlashCommandsToGuilds implements IAfterStartUp {
    protected void addSlashCommandsToTheGuilds() {
        SlashCommandManager slashCommandManager = new SlashCommandManager();
        for (Guild guild : StartUp.publicJDA.getGuilds()) {

            System.out.println(guild);
            //guild.updateCommands().queue();

            for (ISlashCommands iSlashCommands : slashCommandManager.getAllCommands()) {
                System.out.println(slashCommandManager.getAllCommands());

               /* if (iSlashCommands.getClass().isAnnotationPresent(Slash.class) && iSlashCommands.getClass().getAnnotation(Slash.class) != null)
                    continue;*/

                //if (!iSlashCommands.getClass().getAnnotation(Slash.class).active()) continue;


                if (!iSlashCommands.takesArguments())
                    guild.upsertCommand(iSlashCommands.getName().toLowerCase(), iSlashCommands.getDescription()).queue();

                else {
                    guild.upsertCommand(iSlashCommands.getName().toLowerCase(),
                            iSlashCommands.getDescription()).addOptions(iSlashCommands.getOptionData()).queue();

                }


            }

        }
    }

    @Override
    public void doAfterStartUp() {
        addSlashCommandsToTheGuilds();
    }
}

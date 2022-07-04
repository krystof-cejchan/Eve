package listeners;

import commands.purecommands.subparts.GetBotAsUser;
import library_class.LibraryClass;
import main.after_startup.AddingSlashCommandsToGuilds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class FirstJoinServerListener extends ListenerAdapter {
    /**
     * adds all slash commands to the joined guild(server)
     * from documentation:
     * <b>Warning: Discord already triggered a mass amount of these events due to a downtime. Be careful!</b>
     * therefor there is text command "uploadslash" to trigger this method manually
     *
     * @param event {@link GuildJoinEvent}
     */
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        if (event.getGuild().getDefaultChannel() != null)
            event.getGuild().getDefaultChannel().sendMessageEmbeds(generateEmbed().build()).queue();
        AddingSlashCommandsToGuilds.addSlashCommandsToSpecificGuild(event.getGuild());
    }

    private EmbedBuilder generateEmbed() {
        return new EmbedBuilder().setColor(LibraryClass.getRandomColor()).setTitle("Heyyyy!\uD83D\uDC9E")
                .setImage("https://i.gifer.com/1aSc.gif")
                .addField("Slash Commands", "Slash commands will be uploaded soon\nUse **/help** command to get started", false)
                .addField("Text Commands", """
                        You can use text commands right away
                        Default prefix is **;**
                        Use ;help to get started
                        Feel free to change default prefix by using **;prefix** ***X***""", false)
                .setAuthor(GetBotAsUser.getBot().getName(), "https://github.com/krystof-cejchan/Eve",
                        GetBotAsUser.getBot().getAvatarUrl());
    }
}

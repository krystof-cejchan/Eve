package commands;

import commands.commands_others.Birthday;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command shows you info about your server</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _ServerInfo implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        Birthday brth = new Birthday();
        int memberCount = event.getGuild().getMemberCount();
        ArrayList<TextChannel> textChannels = new ArrayList<>(event.getGuild().getTextChannels());
        ArrayList<VoiceChannel> VoiceChannels = new ArrayList<>(event.getGuild().getVoiceChannels());

        EmbedBuilder embed = new EmbedBuilder();

        embed.setAuthor(event.getAuthor().getName());
        embed.setTitle(event.getGuild().getName());

        embed.addField("Birthday: 🎂",
                "\n📅   " + brth.getBirthdayDate(event) + "\n🕑   " + brth.getBirthdayTime(event), false);

        embed.addField("Member Count:", String.valueOf(memberCount), false);

        embed.addField("Channel Count:",
                "Text Channels: " + textChannels.size() + " / " + "Voice Channels: " + VoiceChannels.size(), false);

        event.getMessage().replyEmbeds(embed.build()).queue();

    }

    @Override
    public String getName() {
        return "Server Info";
    }

    @Override
    public String whatDoIDo() {
        return "This command shows you info about your server";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("server");
        t.add("serverinfo");

        return t;
    }

}

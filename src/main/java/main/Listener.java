package main;

import java.util.ArrayList;
import java.util.Objects;

import audio_player.StopCommand;
import commands.CommandManager;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.CurrentTextChannel;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {


    GifSender gifs = new GifSender();

    @SuppressWarnings("unused")
    public void sendGif(String key, String limit, MessageReceivedEvent event) {
        try {

            String zprava = gifs.call_me(key, limit);
            event.getMessage().reply((zprava)).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {
        try {
            leaveIfAlone(event, null, true);
        } catch (Exception e) {
        }

    }

    public void onGuildVoiceMove(@NotNull GuildVoiceMoveEvent event) {
        try {
            leaveIfAlone(null, event, false);
        } catch (Exception e) {
        }
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        try {
            if (!event.getAuthor().isBot() && !containsForbiddenChars(event.getMessage().getContentRaw())) {

                TextChannel re = event.getGuild().getTextChannelById("933515864790159360");
                MessageChannel eventChannel = event.getChannel();
                CurrentTextChannel ctch = new CurrentTextChannel(eventChannel.getId());
                ctch.setIid(eventChannel.getId());
                boolean allower = true;
                if (eventChannel == re || allower) {

                    System.out.println(Prefix.getValue());
                    if (event.getMessage().getContentRaw().substring(0, Prefix.getValue().length())
                            .equalsIgnoreCase(Prefix.getValue())) {
                        CommandManager manager = new CommandManager();
                        if (manager.getCommand(event) != null)
                            Objects.requireNonNull(manager.getCommand(event)).doTask(event);
                    }

                }
            }
        } catch (Exception e) {
            // command was not found
            e.printStackTrace();
        }
    }

    private boolean containsForbiddenChars(String msg) {
        String[] forbiddenChars = {" ", "	"};
        for (String string : forbiddenChars) {
            if (msg.contains(string))
                return true;
        }
        return false;

    }

    private void leaveIfAlone(GuildVoiceLeaveEvent event, GuildVoiceMoveEvent event2, boolean leave) {
        if (leave) {
            AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel();

            assert connectedChannelSelf != null;
            ArrayList<Member> members = new ArrayList<>(connectedChannelSelf.getMembers());

            boolean human = false;
            for (Member member : members) {
                if (!member.getUser().isBot()) {
                    human = true;
                }
            }
            if (!human) {
                VoiceChannels leaveVC = new VoiceChannels();
                StopCommand stop = new StopCommand();
                stop.stopMusic(event);
                leaveVC.Leave(event);
            }
        } else {
            AudioChannel connectedChannelSelf = Objects.requireNonNull(event2.getGuild().getSelfMember().getVoiceState()).getChannel();

            assert connectedChannelSelf != null;
            ArrayList<Member> members = new ArrayList<>(connectedChannelSelf.getMembers());

            boolean human = false;
            for (Member member : members) {
                if (!member.getUser().isBot()) {
                    human = true;
                }
            }
            if (!human) {
                VoiceChannels leaveVC = new VoiceChannels();
                StopCommand stop = new StopCommand();
                stop.stopMusic(event2);
                leaveVC.Leave(event2);
            }
        }

    }

}

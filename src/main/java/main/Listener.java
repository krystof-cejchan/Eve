package main;


import _library_class.GlobalValues;
import audio_player.StopCommand;
import commands.CommandManager;
import commands.commands_others.GifSender;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.CurrentTextChannel;
import objects.MessageReceivedEvent_StaticCustomClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class Listener extends ListenerAdapter {


    GifSender gifs = new GifSender();

    @SuppressWarnings("unused")
    public void sendGif(String key, String limit, MessageReceivedEvent event) {
        try {

            String zprava = gifs.getExtractedGif(key, limit);
            event.getMessage().reply((zprava)).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onGuildVoiceLeave(@NotNull GuildVoiceLeaveEvent event) {
        try {
            leaveIfAlone(event, null, true);
        } catch (Exception ignored) {
        }

    }

    public void onGuildVoiceMove(@NotNull GuildVoiceMoveEvent event) {
        try {
            leaveIfAlone(null, event, false);
        } catch (Exception ignored) {
        }
    }

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        try {
            if (!event.getAuthor().isBot() && !containsForbiddenChars(event.getMessage().getContentRaw())) {

                TextChannel re = event.getGuild().getTextChannelById("933515864790159360");
                MessageChannel eventChannel = event.getChannel();
                CurrentTextChannel currTxtChannel = new CurrentTextChannel(eventChannel.getId());
                currTxtChannel.setIid(eventChannel.getId());
                if (eventChannel == re || GlobalValues.ALLOW_NOT_IN_TEST_MODE) {

                    MessageReceivedEvent_StaticCustomClass.setEvent(event);
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

    private void leaveIfAlone(@Nullable GuildVoiceLeaveEvent event, @Nullable GuildVoiceMoveEvent event2, boolean leave) {
        if (leave) {
            assert event != null;
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
            assert event2 != null;
            AudioChannel connectedChannelSelf = Objects.requireNonNull(event2.getGuild().getSelfMember().getVoiceState()).getChannel();

            boolean human = false;
            assert connectedChannelSelf != null;
            for (Member member : connectedChannelSelf.getMembers()) {
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

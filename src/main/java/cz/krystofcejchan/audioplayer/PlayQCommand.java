package cz.krystofcejchan.audioplayer;

import cz.krystofcejchan.main.VoiceChannels;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * plays a queue
 */
public class PlayQCommand {
    public void playMusic(MessageReceivedEvent event, String url, boolean isLink, boolean playImmediately,
                          boolean multiplyAdded) {
        final MessageChannel channel = event.getChannel();

        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember())
                .getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember()
                .getVoiceState()).getChannel(); // bot

        VoiceChannels vc = new VoiceChannels();

        if (!(connectedChannel == (null))) {

            if (connectedChannel.equals(connectedChannelSelf)) {

                if (isLink) {
                    loadNPlay(channel, url, null, event, null, playImmediately, multiplyAdded);
                }

            } else {
                if (isLink) {
                    vc.Join(event);
                    loadNPlay(channel, url, null, event, null, playImmediately, multiplyAdded);
                }

            }
        } else {
            event.getMessage().reply("where r u?  🥺").queue();
        }

    }

    public void playMusicFromSlash(@NotNull SlashCommandInteractionEvent event, String url, boolean isLink,
                                   boolean playImmediately, boolean multiplyAdded) {
        final MessageChannel channel = event.getChannel();

        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember())
                .getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(Objects.requireNonNull(event.getGuild())
                .getSelfMember().getVoiceState()).getChannel(); // bot

        VoiceChannels vc = new VoiceChannels();

        if (!(connectedChannel == (null))) {

            if (connectedChannel.equals(connectedChannelSelf)) {

                if (isLink) {
                    loadNPlay(channel, url, null, null, event, playImmediately, multiplyAdded);
                }

            } else {
                if (isLink) {
                    vc.joinSlash(event, false);
                    loadNPlay(channel, url, null, null, event, playImmediately, multiplyAdded);
                }

            }
        } else {
            event.reply("where r u?  🥺").setEphemeral(true).queue();
        }

    }


    protected void loadNPlay(MessageChannel channel, String url, SelectMenuInteractionEvent event0, MessageReceivedEvent eventMsg,
                             SlashCommandInteractionEvent eventSlash, boolean playImmediately, boolean multiplyAdded) {
        PlayerManager.getInstance().loadAndPlay(channel, url, true, event0, eventMsg, eventSlash,
                null, null, playImmediately, multiplyAdded);
    }

}
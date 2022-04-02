package audio_player;

import main.VoiceChannels;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * plays a queue
 */
public class PlayQCommand {
    public void playMusic(MessageReceivedEvent event, String url, boolean isLink) {
        final MessageChannel channel = event.getChannel();

        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

        VoiceChannels vc = new VoiceChannels();

        if (!(connectedChannel == (null))) {

            if (connectedChannel.equals(connectedChannelSelf)) {

                if (isLink) {
                    loadNPlay(channel, url, event);
                }

            } else {
                if (isLink) {
                    vc.Join(event);
                    loadNPlay(channel, url, event);
                }

            }
        } else {
            event.getMessage().reply("where r u?  🥺").queue();
        }

    }

    protected void loadNPlay(MessageChannel channel, String url, MessageReceivedEvent event) {
        PlayerManager.getInstance().loadAndPlay(channel, url, true, event, null, null);
    }

}
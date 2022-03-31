package audio_player;

import main.VoiceChannels;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.Objects;

public class PlayCommand {

    public void playMusic(MessageReceivedEvent event, String url, boolean isLink, MessageTypes messageTypes, String voice) {
        final MessageChannel channel = event.getChannel();

        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

        VoiceChannels vc = new VoiceChannels();

        if (!(connectedChannel == (null))) {
            if (connectedChannel.equals(connectedChannelSelf)) {
                if (isLink) {

                    if (url.contains("spotify.com")) {

                        event.getMessage().reply("Spotify is not supported yet").queue();

                    } else loadNPlay(channel, url, event, messageTypes, voice);
                } else {
                    loadNPlay(channel, "ytsearch:" + url, event, messageTypes, voice);
                }

            } else {

                vc.Join(event);
                if (isLink) {
                    loadNPlay(channel, url, event, messageTypes, voice);
                } else {
                    loadNPlay(channel, "ytsearch:" + url, event, messageTypes, voice);
                }

            }
        } else {
            event.getMessage().reply("where r u? 🥺").queue();
        }

    }

    protected void loadNPlay(MessageChannel channel, String url, MessageReceivedEvent event, MessageTypes type, String voice) {
        PlayerManager.getInstance().loadAndPlay(channel, url, false, event, type, voice);
    }

}

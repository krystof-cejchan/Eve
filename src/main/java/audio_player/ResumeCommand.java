package audio_player;

import main.VoiceChannels;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.Objects;

public class ResumeCommand {

    public void resumeMusic(MessageReceivedEvent event) {

        //event.getChannel();

        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel();

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

        new VoiceChannels();

        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {

                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {

                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());

                    if (musicManager.SCHEDULER.PLAYER.isPaused()) {
                        musicManager.SCHEDULER.PLAYER.setPaused(false);

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

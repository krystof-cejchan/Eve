package audio_player;

import javax.annotation.Nullable;

import main.VoiceChannels;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ResumeCommand {

	public void resumeMusic(MessageReceivedEvent event) {

		event.getChannel();

		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel();

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		new VoiceChannels();

		try {
			if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {

				if (connectedChannel.equals(connectedChannelSelf)) {

					final GuildMusicManager musicManager = PlayerManager.getInstance()
							.getMusicManager(event.getGuild());

					if (musicManager.SCHEDULER.PLAYER.isPaused()) {
						musicManager.SCHEDULER.PLAYER.setPaused(false);

					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

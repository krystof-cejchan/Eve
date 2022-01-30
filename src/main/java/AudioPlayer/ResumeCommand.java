package AudioPlayer;

import javax.annotation.Nullable;

import Main.VoiceChannels;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ResumeCommand {

	public void resumeMusic(MessageReceivedEvent event) {

		event.getChannel();

		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState()
				.getChannel(); /* event.getMember().getVoiceState().getChannel(); */ // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		new VoiceChannels();

		try {
			if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
				// uživatel někde je a bot taky
				if (connectedChannel.equals(connectedChannelSelf)) {

					final GuildMusicManager musicManager = PlayerManager.getInstance()
							.getMusicManager(event.getGuild());
					// System.out.print(musicManager.SCHEDULER.PLAYER.);
					if (musicManager.SCHEDULER.PLAYER.isPaused()) {
						musicManager.SCHEDULER.PLAYER.setPaused(false);
						// System.out.println("im paused");
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

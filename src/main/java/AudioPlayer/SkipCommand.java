package AudioPlayer;

import javax.annotation.Nullable;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SkipCommand {
	public void skipTrack(MessageReceivedEvent event) {
		//event.getChannel();

		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		//new VoiceChannels();

		try {
			if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
				// uživatel někde je a bot taky
				if (connectedChannel.equals(connectedChannelSelf)) {

					final GuildMusicManager musicManager = PlayerManager.getInstance()
							.getMusicManager(event.getGuild());
					final AudioPlayer audioPlayer = musicManager.AUDIOPLAYER;

					// System.out.print(musicManager.SCHEDULER.PLAYER.);
					if (audioPlayer.getPlayingTrack() == null) {
						event.getChannel().sendMessage("Queue is empty").queue();
					} else {
						musicManager.SCHEDULER.nextTrack();
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

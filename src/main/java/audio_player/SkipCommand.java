package audio_player;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Nullable;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SkipCommand {
	public void skipTrack(MessageReceivedEvent event, boolean msg) {
		// event.getChannel();

		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		// new VoiceChannels();

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

						BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;
						ArrayList<AudioTrack> audioList = new ArrayList<>(queue);
						String nextTrackName = audioList.get(0).getInfo().title;
						if (msg)
							event.getChannel().sendMessage("Skipped to the next song!\n**" + nextTrackName + "**")
									.queue();
						musicManager.SCHEDULER.nextTrack();
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void skipTrackTo(MessageReceivedEvent event, int index) {
		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		// new VoiceChannels();

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

						int i;
						for (i = 0; i < index; i++) {

							skipTrack(event, false);

						}

						NowPlayingCommand n = new NowPlayingCommand();
						event.getChannel().sendMessage(
								"Successfully skipped to: \n**" + n.getNpAudioTrack(event).getInfo().title + "**")
								.queue();
						// skipTrack(event,false);
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

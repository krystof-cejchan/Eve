package Commands;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;

import AudioPlayer.GuildMusicManager;
import AudioPlayer.NowPlayingCommand;
import AudioPlayer.PlayerManager;
import Main.LibraryClass;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _FF implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		String[] args = event.getMessage().getContentRaw().split(" ");
		if (args.length > 1) {
			int ff = Integer.valueOf(args[1]) * 1000;
			@Nullable
			AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

			@Nullable
			AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

			try {
				if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
					// uživatel někde je a bot taky
					if (connectedChannel.equals(connectedChannelSelf)) {

						final GuildMusicManager musicManager = PlayerManager.getInstance()
								.getMusicManager(event.getGuild());
						final AudioPlayer audioPlayer = musicManager.AUDIOPLAYER;
						if (audioPlayer.getPlayingTrack() != null) {
							long dur = audioPlayer.getPlayingTrack().getDuration();
							int pos = (int) audioPlayer.getPlayingTrack().getPosition();
							if (audioPlayer.getPlayingTrack()
									.getDuration() > audioPlayer.getPlayingTrack().getPosition() + ff)// if statement
							{
								audioPlayer.getPlayingTrack()
										.setPosition(audioPlayer.getPlayingTrack().getPosition() + ff);

								event.getChannel()
										.sendMessage("FastForwarding from __" + NowPlayingCommand.getTimestamp(pos)
												+ "__ → __" + NowPlayingCommand.getTimestamp(pos + ff)
												+ "__  \nSong duration: " + NowPlayingCommand.getTimestamp(dur))
										.queue();
								LibraryClass.addReactionToTheMsg(event, "U+23E9");
							} else {
								event.getChannel().sendMessage("Song duration exceeded").queue();
								;
							}

						}

					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Fast-Forward the current track";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command fast-forwards current track (in seconds)";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("ff");
		t.add("fastforward");
		return t;
	}

}

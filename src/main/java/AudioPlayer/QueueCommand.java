package AudioPlayer;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

public class QueueCommand {

	public void getQueue(MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();

		GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
		BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;

		/*
		 * if (queue.isEmpty()) { event.getChannel().sendMessage("The queue is empty");
		 * return; }
		 */
		if (queue.size() < 1 && musicManager.SCHEDULER.PLAYER.getPlayingTrack() != null) {
			event.getChannel().sendMessage("The queue contains only one track").queue();
			NowPlayingCommand np = new NowPlayingCommand();
			np.getNowPlayingTrack(event);
			
			return;
		}
		if (!queue.isEmpty()) {

			int shownTrackCount = 10;
			final int trackCount = Math.min(queue.size(), shownTrackCount);
			final ArrayList<AudioTrack> trackList = new ArrayList<>(queue);

			MessageAction messageAction = channel.sendMessage("**QUEUE**\n");

			for (int i = 0; i < trackCount; i++) {
				AudioTrack track = trackList.get(i);
				AudioTrackInfo info = track.getInfo();
				int ii = i + 1;
				messageAction.append(("*" + ii + "*") + ".").append(" `").append(info.title).append("` \n");
			}
			// if (trackList.size() <= trackCount) {
			int restCountTrack = trackList.size() - shownTrackCount;
			String trackSmore = "";

			if (trackList.size() > shownTrackCount) {
				if (restCountTrack < 2) {
					trackSmore = " more track";
				} else {
					trackSmore = " more tracks";
				}
				messageAction.append("\n+").append(String.valueOf(restCountTrack)).append(trackSmore);
				
				messageAction.queue();

			} else {
				messageAction.queue();
			}

			// }

		}
		if(queue.isEmpty()) {
			event.getChannel().sendMessage("Queue is empty").queue();
		}
	}

}

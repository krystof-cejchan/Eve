package audio_player;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

public class QueueCommand {

	public void getQueue(MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();

		GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
		BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;

		if (queue.size() < 1 && musicManager.SCHEDULER.PLAYER.getPlayingTrack() != null) {
			event.getChannel().sendMessage("The queue contains only one track").queue();
			NowPlayingCommand np = new NowPlayingCommand();
			np.getNowPlayingTrack(event);

			return;
		}
		if (!queue.isEmpty()) {

			int shownTrackCount = 30;
			final int trackCount = Math.min(queue.size(), shownTrackCount);
			final ArrayList<AudioTrack> trackList = new ArrayList<>(queue);

			MessageAction messageAction = channel.sendMessage("**QUEUE**\n");

			for (int i = 0; i < trackCount; i++) {
				AudioTrack track = trackList.get(i);
				AudioTrackInfo info = track.getInfo();
				int ii = i + 1;
				messageAction.append(("*" + ii + "*") + ".").append(" `").append(info.title).append("` \n");
			}

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

		}
		if (queue.isEmpty()) {
			event.getChannel().sendMessage("Queue is empty like my life").queue();
		}
	}

	public void removeFromQueue(MessageReceivedEvent event, int index) {

		GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
		BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;

		if (!queue.isEmpty()) {

			try {
				ArrayList<AudioTrack> audioList = new ArrayList<>(queue);
				String deletedTitle = audioList.get(index).getInfo().title;
				audioList.remove(index);

				musicManager.SCHEDULER.QUEUE.clear();

				for (AudioTrack audioTrack : audioList) {
					musicManager.SCHEDULER.QUEUE.add(audioTrack);
				}
				event.getChannel().sendMessage("```diff\n" + "-")
						.append(deletedTitle + " \nhas been thrown into the void!```").queue();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		if (queue.isEmpty()) {
			event.getChannel().sendMessage("Q seems to be empty").queue();
		}
	}

	public void removeFromQueuebyName(MessageReceivedEvent event, AudioTrack aTrack) {

		GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
		BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;

		if (!queue.isEmpty()) {

			try {
				ArrayList<AudioTrack> audioList = new ArrayList<>(queue);
				String deletedTitle = aTrack.getInfo().title;
				audioList.remove(aTrack);

				musicManager.SCHEDULER.QUEUE.clear();

				for (AudioTrack audioTrack : audioList) {
					musicManager.SCHEDULER.QUEUE.add(audioTrack);
				}
				event.getChannel().sendMessage("```diff\n" + "-")
						.append(deletedTitle + " \nhas been thrown into the void!```").queue();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		if (queue.isEmpty()) {
			event.getChannel().sendMessage("Q seems to be empty").queue();
		}
	}

}

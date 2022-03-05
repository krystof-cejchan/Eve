package commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import _library_class.LibraryClass;
import audio_player.GuildMusicManager;
import audio_player.PlayerManager;
import audio_player.QueueCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

public class _Remove implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String[] args = event.getMessage().getContentRaw().split(" ");

		try {
			if (args.length > 1) {
				QueueCommand queueCommand = new QueueCommand();
				if (event.getMessage().getContentRaw().matches(".*\\d.*"))// check for numbers
				{

					queueCommand.removeFromQueue(event, Integer.valueOf(args[1]) - 1);
				} else {
					ArrayList<String> text = new ArrayList<>();
					for (int i = 1; i < args.length; i++) {
						text.add(args[i]);
					}

					SimilarityStrategy strategy = new JaroWinklerStrategy();
					StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
					GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
					BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;
					HashMap<AudioTrack, Double> similarityMap = new HashMap<>();
					for (AudioTrack track : queue) {
						similarityMap.put(track, service.score(track.getInfo().title,
								LibraryClass.getStringFromArrayOfStrings_withSpaces(text)));

					}
					queueCommand.removeFromQueuebyName(event,
							LibraryClass.getTheMostSuitableItemFromAHashMap(similarityMap, queue));
				}

			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Remove a track";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command removes *x.* track from the queue";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("remove");
		t.add("del");
		t.add("delete");
		return t;
	}

}

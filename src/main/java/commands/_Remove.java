package commands;

import _library_class.LibraryClass;
import audio_player.GuildMusicManager;
import audio_player.PlayerManager;
import audio_player.QueueCommand;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
/**
 * <p style="background-color:Green;">This command removes *x.* track from the queue</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Remove implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        try {
            if (args.length > 1) {
                QueueCommand queueCommand = new QueueCommand();
                if (event.getMessage().getContentRaw().matches(".*\\d.*"))// check for numbers
                {

                    queueCommand.removeFromQueue(event, Integer.parseInt(args[1]) - 1);
                } else {
                    ArrayList<String> text = new ArrayList<>(Arrays.asList(args).subList(1, args.length));

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
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {

        return "Remove a track";
    }

    @Override
    public String whatDoIDo() {

        return "This command removes *x.* track from the queue";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("remove");
        t.add("del");
        t.add("delete");
        return t;
    }

}

package audio_player;

import _library_class.LibraryClass;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Skipping a track or more tracks
 */
public class SkipCommand {
    /**
     * Skips only the following track
     *
     * @param event {@link MessageReceivedEvent}
     * @param msg   if true, bot will send a message to the text channel informing users about skipping song; false, bot will skip a song without letting the user know
     * @author krystof-cejchan
     */
    public void skipTrack(MessageReceivedEvent event, boolean msg) {


        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot


        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {

                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {

                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                    final AudioPlayer audioPlayer = musicManager.AUDIOPLAYER;


                    if (audioPlayer.getPlayingTrack() == null) {
                        event.getChannel().sendMessage("Queue is empty!").queue();
                        return;
                    }
                    System.out.println(musicManager.SCHEDULER.QUEUE.size());
                    if (musicManager.SCHEDULER.QUEUE.size() < 1) {
                        event.getChannel().sendMessage("Skipping the only song in your queue!").queue();
                        musicManager.AUDIOPLAYER.stopTrack();
                        return;
                    }

                    BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;

                    ArrayList<AudioTrack> audioList = new ArrayList<>(queue);
                    // String nextTrackName = audioList.get(0).getInfo().title;
                    if (msg)
                        event.getChannel().sendMessage("Skipped to the next song! ```fix\n" +
                                audioList.get(0).getInfo().title/* title of the following track */ + "\n" +
                                "```").queue();
                    musicManager.SCHEDULER.nextTrack();


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Skips to the track with index in the queue
     *
     * @param event {@link MessageReceivedEvent}
     * @param index a number referring to the song in the queue
     * @author krystof-cejchan
     */
    public void skipTrackTo(MessageReceivedEvent event, int index) {
        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot


        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {

                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                    final AudioPlayer audioPlayer = musicManager.AUDIOPLAYER;

                    if (audioPlayer.getPlayingTrack() == null) {
                        event.getChannel().sendMessage("Queue is empty").queue();
                    } else {


                        for (int i = 0; i < index; i++) {
                            skipTrack(event, false);
                        }

                        NowPlayingCommand n = new NowPlayingCommand();
                        event.getChannel().sendMessage("Successfully skipped to: \n**" + n.getNpAudioTrack(event).getInfo().title + "**").queue();
                        // skipTrack(event,false);
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * skips to the song which is the most suitable one from the user's input
     * uses skipTrackTo
     *
     * @param event      {@link MessageReceivedEvent}
     * @param usersInput user's input
     * @throws NullPointerException in case that the track does not suit any of the tracks in the arraylist
     */
    public void skipToTrackbyTitle(MessageReceivedEvent event, String usersInput) throws NullPointerException {
        ArrayList<String> text = new ArrayList<>(Arrays.asList(usersInput.split(" ")).subList(1, Arrays.asList(usersInput.split(" ")).size()));

        SimilarityStrategy strategy = new JaroWinklerStrategy();
        StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;
        HashMap<AudioTrack, Double> similarityMap = new HashMap<>();

        for (AudioTrack track : queue) {
            similarityMap.put(track, service.score(track.getInfo().title,
                    LibraryClass.getStringFromArrayOfStrings_withSpaces(text)));


        }
        if (LibraryClass.getTheMostSuitableAudioTrackFromAHashMap(similarityMap, queue) != null) {
            AudioTrack audioTrack = LibraryClass.getTheMostSuitableAudioTrackFromAHashMap(similarityMap, queue);


            if (!queue.isEmpty()) {

                try {
                    ArrayList<AudioTrack> audioList = new ArrayList<>(queue);
                    assert audioTrack != null;
                    String toSkippedTrack = audioTrack.getInfo().title;
                    skipTrackTo(event, getIndex(audioList, audioTrack) + 1);
                    event.getChannel().sendMessage("Skipped to " + toSkippedTrack).queue();
                } catch (Exception ignored) {

                }

            } else {
                event.getChannel().sendMessage("Queue seems to be empty").queue();
            }

        }


    }

    private int getIndex(ArrayList<AudioTrack> audioList, AudioTrack audioTrack) {
        int startOfSubArray = 0;
        for (AudioTrack track : audioList) {
            if (!track.equals(audioTrack))
                startOfSubArray++;
            else {
                return startOfSubArray;
            }
        }
        return -1;
    }

}


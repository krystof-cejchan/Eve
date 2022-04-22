package audio_player;

import _library_class.Global_Values;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 * Queue related matters
 * Skipping, removing ...
 */
public class QueueCommand {
    /**
     * Prints out queue to the text channel
     *
     * @param event {@link MessageReceivedEvent}
     * @author krystof-cejchan
     */
    @SuppressWarnings("all")
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

            int shownTrackCount = Global_Values.shownTrackCount;
            final int trackCount = Math.min(queue.size(), shownTrackCount);
            final ArrayList<AudioTrack> trackList = new ArrayList<>(queue);

            MessageAction messageAction = channel.sendMessage("**QUEUE**\n");


            messageAction.append("__").append("Currently playing").append("__").append(":").append(" `").append(musicManager.SCHEDULER.PLAYER.getPlayingTrack().getInfo().title).append("` \n");
            for (int i = 0; i < trackCount; i++) {
                AudioTrack track = trackList.get(i);
                AudioTrackInfo info = track.getInfo();
                int ii = i + 1;
                messageAction.append("*").append(String.valueOf(ii)).append("*").append(".").append(" `").append(info.title).append("` \n");
            }

            int restCountTrack = trackList.size() - shownTrackCount;
            String trackSmore;

            if (trackList.size() > shownTrackCount) {
                if (restCountTrack < 2) {
                    trackSmore = " more track";
                } else {
                    trackSmore = " more tracks";
                }
                messageAction.append("\n+").append(String.valueOf(restCountTrack)).append(trackSmore).queue();

                messageAction.queue();

            } else {
                messageAction.queue();
            }

        }
        if (queue.isEmpty()) {
            event.getChannel().sendMessage("Queue is empty   ✨***void*** ✨").queue();
        }
    }


    /**
     * Removes an item from the queue by index
     *
     * @param event {@link MessageReceivedEvent}
     * @param index what item should be removed
     * @author krystof-cejchan
     */
    public void removeFromQueue(MessageReceivedEvent event, int index) {

        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;

        if (!queue.isEmpty()) {

            try {
                ArrayList<AudioTrack> audioList = new ArrayList<>(queue);
                String deletedTitle = audioList.get(index).getInfo().title;
                audioList.remove(index);

                musicManager.SCHEDULER.QUEUE.clear();

                musicManager.SCHEDULER.QUEUE.addAll(audioList);
                event.getChannel().sendMessage("```diff\n" + "-").append(deletedTitle).append("\nhas been thrown into the void!```").queue();
            } catch (Exception ignored) {

            }

        }
        if (queue.isEmpty()) {
            event.getChannel().sendMessage("Queue seems to be empty").queue();
        }
    }

    /**
     * If possible, removes a track by its title
     *
     * @param event  {@link MessageReceivedEvent}
     * @param aTrack {@link AudioTrack} this track gets removed from the QUEUE, since the queue is just a list of {@link AudioTrack}s, if aTrack equals any item from the QUEUE, it gets removed
     * @author krystof-cejchan
     */
    public void removeFromQueuebyName(MessageReceivedEvent event, AudioTrack aTrack) {

        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;

        if (!queue.isEmpty()) {

            try {
                ArrayList<AudioTrack> audioList = new ArrayList<>(queue);
                String deletedTitle = aTrack.getInfo().title;
                audioList.remove(aTrack);

                musicManager.SCHEDULER.QUEUE.clear();

                musicManager.SCHEDULER.QUEUE.addAll(audioList);
                event.getChannel().sendMessage("```diff\n" + "-").append(deletedTitle).append("\nhas been thrown into the void!```").queue();
            } catch (Exception ignored) {

            }

        }
        if (queue.isEmpty()) {
            event.getChannel().sendMessage("Queue seems to be empty").queue();
        }
    }
}

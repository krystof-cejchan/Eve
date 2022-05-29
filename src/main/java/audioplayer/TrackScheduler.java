package audioplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import objects.MessageReceivedEvent_StaticCustomClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Track Scheduler
 */
public class TrackScheduler extends AudioEventAdapter {
    public final AudioPlayer PLAYER;
    public final BlockingQueue<AudioTrack> QUEUE;

    public TrackScheduler(AudioPlayer player) {
        this.PLAYER = player;
        this.QUEUE = new LinkedBlockingQueue<>();
    }

    public void queue(AudioTrack track) {
        if (!this.PLAYER.startTrack(track, true)) {
            this.QUEUE.offer(track);
        }

    }

    /**
     * when a track comes to an end
     *
     * @param player    {@link AudioPlayer}
     * @param track     {@link AudioTrack}
     * @param endReason {@link AudioTrackEndReason}
     */
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (endReason.mayStartNext) {
            nextTrack();
            LetUsersKnowOfPlayingNextTrack.informAboutPlayingNextTrack(MessageReceivedEvent_StaticCustomClass.getEvent());
        }
    }

    public void nextTrack() {
        this.PLAYER.startTrack(this.QUEUE.poll(), false);
    }

    public void shuffle() {
        ArrayList<AudioTrack> arrayList = new ArrayList<>(QUEUE);
        QUEUE.clear();
        Collections.shuffle(arrayList);
        QUEUE.addAll(arrayList);
    }

}

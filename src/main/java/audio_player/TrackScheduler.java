package audio_player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

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

	public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
		if (endReason.mayStartNext) {
			nextTrack();
		}
	}

	public void nextTrack() {
		this.PLAYER.startTrack(this.QUEUE.poll(), false);
	}

	public void shuffle() {
		ArrayList<AudioTrack> arrayList = new ArrayList<>(QUEUE);
		QUEUE.clear();
		Collections.shuffle(arrayList);
		for (AudioTrack audioTrack : arrayList) {
			QUEUE.add(audioTrack);
		}
	}

}

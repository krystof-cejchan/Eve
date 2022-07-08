package cz.krystofcejchan.audioplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
    public final AudioPlayer AUDIOPLAYER;
    public final TrackScheduler SCHEDULER;
    private final AudioPlayerSendHandler SENDHANDLER;

    public GuildMusicManager(AudioPlayerManager manager) {
        this.AUDIOPLAYER = manager.createPlayer();
        this.SCHEDULER = new TrackScheduler(AUDIOPLAYER);
        this.AUDIOPLAYER.addListener(SCHEDULER);
        this.SENDHANDLER = new AudioPlayerSendHandler(AUDIOPLAYER);
    }

    public AudioPlayerSendHandler getSendHandler() {
        return SENDHANDLER;
    }

}

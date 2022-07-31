package cz.krystofcejchan.commands.purecommands.playsongcommand_pure;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import cz.krystofcejchan.audioplayer.GuildMusicManager;
import cz.krystofcejchan.enums_annotations_exceptions.exceptions.NoTrackMatchException;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.entities.Guild;
import org.apache.commons.collections4.map.HashedMap;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class PlayerManagerPure {
    private static PlayerManagerPure INSTANCE;

    private final Map<Long, GuildMusicManager> MUSICMANAGERS;
    private final AudioPlayerManager AUDIOPLAYERMANAGER;


    public PlayerManagerPure() {
        this.MUSICMANAGERS = new HashedMap<>();
        this.AUDIOPLAYERMANAGER = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(AUDIOPLAYERMANAGER);
        AudioSourceManagers.registerLocalSource(AUDIOPLAYERMANAGER);
    }

    /**
     * Singleton design pattern
     *
     * @return instance of the class, if any other instance does not exist
     */
    public static PlayerManagerPure getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManagerPure();
        }
        return INSTANCE;
    }

    public GuildMusicManager getMusicManager(net.dv8tion.jda.api.entities.Guild guild) {
        return this.MUSICMANAGERS.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(AUDIOPLAYERMANAGER);
            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    public void loadAndPlay(Guild guild, String url, boolean isQueue) {

        final GuildMusicManager musicManager = this.getMusicManager(guild);

        this.AUDIOPLAYERMANAGER.loadItemOrdered(musicManager, url, new AudioLoadResultHandler() {

            /**
             * single track loaded
             *
             * @param track {@link AudioTrack} which is supposed to be added to the queue
             */
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.SCHEDULER.queue(track);
            }

            /**
             * playlist loaded
             *
             * @param playlist a list of {@link AudioTrack}-s
             */
            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

                List<AudioTrack> tracks = playlist.getTracks();
                if (isQueue) {
                    tracks.forEach(musicManager.SCHEDULER::queue);

                } else {
                    AudioTrack track = tracks.get(0);
                    musicManager.SCHEDULER.queue(track);

                }
            }

            /**
             * no track with this title or upload under this url was found
             */
            @Override
            public void noMatches() {
                try {
                    throw new NoTrackMatchException("nothing matches");
                } catch (NoTrackMatchException e) {
                    e.printStackTrace();
                }
            }

            /**
             * track could not be loaded
             * @param exception {@link FriendlyException} this exception will be printed out to the user as an error message
             */
            @Override
            public void loadFailed(FriendlyException exception) {
                exception.printStackTrace();
            }

        });
    }

    public static List<AudioTrack> acceptedTracks = new ArrayList<>();
    boolean found = false;

    public synchronized List<AudioTrack> loadAndReturnAudioTracks(@NotNull Guild guild, @NotNull String url) {
        boolean isUrl = UtilityClass.isLink(url);

        url = !isUrl && !url.startsWith("ytsearch:") ? "ytsearch:" + url : url;
        System.out.println(url);

        final GuildMusicManager musicManager = this.getMusicManager(guild);

        this.AUDIOPLAYERMANAGER.loadItemOrdered(musicManager, url, new AudioLoadResultHandler() {

            /**
             * single track loaded
             *
             * @param track {@link AudioTrack} which is supposed to be added to the queue
             */
            @Override
            public void trackLoaded(AudioTrack track) {
                System.out.println("play");

                found = true;
                acceptedTracks.add(track);
            }

            /**
             * playlist loaded
             *
             * @param playlist a list of {@link AudioTrack}-s
             */
            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                found = true;
                System.out.println("playlist");
                if (isUrl)
                    acceptedTracks.addAll(playlist.getTracks());
                else
                    acceptedTracks.add(playlist.getTracks().get(0));
            }

            /**
             * no track with this title or upload under this url was found
             */
            @Override
            public void noMatches() {
                System.out.println("no match");
            }

            /**
             * track could not be loaded
             * @param exception {@link FriendlyException} this exception will be printed out to the user as an error message
             */
            @Override
            public void loadFailed(FriendlyException exception) {
                exception.printStackTrace();
            }

        });
        final CountDownLatch latch = new CountDownLatch(100);
        System.out.println(acceptedTracks.size());
        while (acceptedTracks.isEmpty() && found) {
            System.out.println("waiting");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return acceptedTracks;
    }

}



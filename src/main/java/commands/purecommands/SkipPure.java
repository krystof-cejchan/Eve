package commands.purecommands;

import audioplayer.GuildMusicManager;
import audioplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class SkipPure {
    public static void skipTrackTo(@Nullable AudioChannel usersJoinedChannel, @Nullable AudioChannel botsJoinedChannel,
                                   @NotNull Guild guild, Integer pos) {
        if (pos == null)
            pos = 1;

        try {
            if (!(usersJoinedChannel == (null)) || !(botsJoinedChannel == (null))) {

                assert usersJoinedChannel != null;
                if (usersJoinedChannel.equals(botsJoinedChannel)) {

                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
                    final AudioPlayer audioPlayer = musicManager.AUDIOPLAYER;


                    if (audioPlayer.getPlayingTrack() == null) {
                        return;
                    }
                    System.out.println(musicManager.SCHEDULER.QUEUE.size());
                    if (musicManager.SCHEDULER.QUEUE.size() < 1) {
                        musicManager.AUDIOPLAYER.stopTrack();
                        return;
                    }

                    for (int i = 0; i < pos; i++) {

                        musicManager.SCHEDULER.nextTrack();
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

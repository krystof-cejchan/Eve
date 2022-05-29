package commands.purecommands.subparts;

import audioplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import javax.annotation.Nullable;

/**
 * a simple class containing one method that returns {@link com.sedmelluq.discord.lavaplayer.track.AudioTrack} that is currently being played
 */
public class GetCurrentTrack {
    @Nullable
    public static AudioTrack getTrack(Guild guild) {
        return PlayerManager.getInstance().getMusicManager(guild).AUDIOPLAYER.getPlayingTrack();
    }
}

package cz.krystofcejchan.commands.purecommands.subparts;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Objects;

public class LetCurrentTrackFastForwardFrom0 extends GetCurrentTrack {
    /**
     * @param guild {@link Guild}
     * @return -1 → no song to fastforward<br> -2 → any other error<br><br>positive number → success
     */
    public static int fastForward(Guild guild, int secToSkip) {
        if (getTrack(guild) == null) return -1;

        AudioTrack currTrack = getTrack(guild);
        if (secToSkip >= 0 && secToSkip <= Objects.requireNonNull(currTrack).getDuration()) {
            currTrack.setPosition(secToSkip);
            return 1;
        }
        else{return -2;}
    }
}

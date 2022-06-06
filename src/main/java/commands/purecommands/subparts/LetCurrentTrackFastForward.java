package commands.purecommands.subparts;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Objects;

public class LetCurrentTrackFastForward extends GetCurrentTrack {
    public static int fastForward(Guild guild, int secToSkip) {
        if (getTrack(guild) == null) return -1;

        AudioTrack currTrack = getTrack(guild);
        assert currTrack != null;
        int currPos = (int) currTrack.getPosition();
        if (secToSkip >= 0 && secToSkip + currPos <= Objects.requireNonNull(currTrack).getDuration()) {
            currTrack.setPosition(currPos + secToSkip);
            return 1;
        } else {
            return -2;
        }
    }
}

package commands.purecommands.subparts;

import audioplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;

public class GetCurrentVolume {
    public static int getVolume(Guild guild) {
        return PlayerManager.getInstance().getMusicManager(guild).AUDIOPLAYER.getVolume();
    }
}

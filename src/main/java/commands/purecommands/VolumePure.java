package commands.purecommands;

import audioplayer.GuildMusicManager;
import audioplayer.PlayerManager;
import enums_annotations_exceptions.enums.VolumeUpDown;
import net.dv8tion.jda.api.entities.Guild;

public class VolumePure {


    public static void setVolumeTo(final Guild guild, final int vol) {
        if (vol > 200 || vol < 0) return;
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        if (!(musicManager.AUDIOPLAYER.getVolume() == vol))
            musicManager.AUDIOPLAYER.setVolume(vol);
    }

    public static void setVolumeUpOrDown(Guild guild, VolumeUpDown upOrDown) {

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);


        if (upOrDown == VolumeUpDown.UP) {
            if (!(musicManager.AUDIOPLAYER.getVolume() > 190))
                musicManager.AUDIOPLAYER.setVolume(musicManager.AUDIOPLAYER.getVolume() + 10);
        } else {
            if (!(musicManager.AUDIOPLAYER.getVolume() < 10))
                musicManager.AUDIOPLAYER.setVolume(musicManager.AUDIOPLAYER.getVolume() - 10);

        }

    }
}

package cz.krystofcejchan.commands.commands_slash.pause_resume;

import cz.krystofcejchan.audioplayer.GuildMusicManager;
import cz.krystofcejchan.audioplayer.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;

import java.util.Objects;

public class Logic {
    public static void pauseOrResume(Guild guild, boolean pause) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(guild));
        if (musicManager.SCHEDULER.PLAYER.isPaused() != pause)
            musicManager.SCHEDULER.PLAYER.setPaused(pause);
    }
}

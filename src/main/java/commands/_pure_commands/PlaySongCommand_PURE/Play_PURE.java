package commands._pure_commands.PlaySongCommand_PURE;

import _library_class.LibraryClass;
import commands._pure_commands.subparts.GetUsersVoiceChannels;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;

import javax.annotation.Nullable;
import java.util.Objects;

public class Play_PURE {
    /**
     * @param user user's joined VoiceChannel
     * @param bot  bot's joined VoiceChannel
     * @param url  song title or url
     * @return true or false <b>false means something has gone wrong</b>
     */
    public boolean playMusic(Guild guild, AudioChannel user, AudioChannel bot, String url, boolean isQueue) {

        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(user); // user

        @Nullable AudioChannel connectedChannelSelf = bot; // bot

        if (!connectedChannel.equals(connectedChannelSelf)) {

            GetUsersVoiceChannels.joinAudioChannel(user, guild);

        }
        return triggerPlayingTrackDependingOnUrl(url, guild, isQueue);
    }

    /**
     * depending on url, bot will start playing music
     *
     * @param url     track url or title
     * @param guild   {@link Guild}
     * @param isQueue whether is playlist or not
     * @return if false, then something has gone wrong
     */
    private boolean triggerPlayingTrackDependingOnUrl(String url, Guild guild, boolean isQueue) {
        if (LibraryClass.isLink(url)) {

            if (url.contains("spotify.com")) {
                return false;

            } else {
                loadNPlay(guild, url, isQueue);
                return true;
            }

        } else {
            loadNPlay(guild, "ytsearch:" + url, isQueue);
            return true;
        }
    }

    protected void loadNPlay(Guild guild, String url, boolean isQueue) {
        PlayerManager_PURE.getInstance().loadAndPlay(guild, url, isQueue);
    }
}

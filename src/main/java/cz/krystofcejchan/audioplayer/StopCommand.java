package cz.krystofcejchan.audioplayer;

import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * pauses and stops the playing tracks
 */
public class StopCommand {
    /**
     * pauses the currently playing track
     *
     * @param event {@link MessageReceivedEvent}
     * @author krystof-cejchan
     */
    public void pauseMusic(MessageReceivedEvent event) {

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {

                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {

                    if (!musicManager.SCHEDULER.PLAYER.isPaused()) {
                        musicManager.SCHEDULER.PLAYER.setPaused(true);

                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Stops playing songs, removes all songs from the queue and destroys it
     *
     * @param event {@link MessageReceivedEvent}
     */
    public void stopMusic(MessageReceivedEvent event) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot
        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {

                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {

                    musicManager.SCHEDULER.PLAYER.stopTrack();
                    musicManager.SCHEDULER.QUEUE.clear();
                    musicManager.SCHEDULER.PLAYER.destroy();

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops playing songs, removes all songs from the queue and destroys it <b>when users leave the voice channel and the bot is left alone</b>
     *
     * @param event {@link GuildVoiceLeaveEvent}
     * @author krystof-cejchan
     */
    public void stopMusic(GuildVoiceLeaveEvent event) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());

        try {

            musicManager.SCHEDULER.PLAYER.stopTrack();
            musicManager.SCHEDULER.QUEUE.clear();
            musicManager.SCHEDULER.PLAYER.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Stops playing songs, removes all songs from the queue and destroys it <b>when users are moved away from the voice channel and the bot is left alone</b>
     *
     * @param event {@link GuildVoiceMoveEvent}
     * @author krystof-cejchan
     */
    public void stopMusic(GuildVoiceMoveEvent event) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());

        try {

            musicManager.SCHEDULER.PLAYER.stopTrack();
            musicManager.SCHEDULER.QUEUE.clear();
            musicManager.SCHEDULER.PLAYER.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

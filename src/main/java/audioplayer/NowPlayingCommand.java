package audioplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import library_class.LibraryClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Command dealing with returning or printing out the currently playing track
 *
 * @author krystof-cejchan
 */
public class NowPlayingCommand {

    /**
     * @param milliseconds milliseconds
     * @return meaningful time value according to format<br> {@code .format("%02d:%02d:%02d", hours, minutes, seconds)}
     * or {@code .format("%02d:%02d", minutes, seconds)}
     */
    public static String getTimestamp(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

        if (hours > 0) return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        else return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Sends the currently playing track to the text channel
     *
     * @param event {@link MessageReceivedEvent}
     * @author krystof-cejchan
     */
    public void getNowPlayingTrack(MessageReceivedEvent event) {

        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember())
                .getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember()
                .getVoiceState()).getChannel(); // bot

        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {

                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {

                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                    final AudioPlayer audioPlayer = musicManager.AUDIOPLAYER;

                    // System.out.print(musicManager.SCHEDULER.PLAYER.);
                    if (audioPlayer.getPlayingTrack() == null) {
                        event.getChannel().sendMessage("Nothing is playing").queue();
                    } else {
                        AudioTrack track = audioPlayer.getPlayingTrack();

                        AudioTrackInfo info = track.getInfo();
                        String position = getTimestamp(track.getPosition());
                        String duration = getTimestamp(track.getDuration());
                        String timeLeft = getTimestamp(track.getDuration() - track.getPosition());
                        EmbedBuilder embed = new EmbedBuilder();
                        embed.setTitle("NOW PLAYING");
                        embed.setAuthor(event.getAuthor().getName(), null, event.getAuthor().getAvatarUrl());
                        embed.setColor(LibraryClass.getRandomColor());
                        embed.addField("Title:", info.title, false);
                        embed.addField("By:", info.author, false);
                        embed.addField("Duration", position + "  /  " + duration + " \nTime left: " + timeLeft, false);
                        event.getChannel().sendMessageEmbeds(embed.build()).queue();
                        embed.clear();
                    }

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param event {@link MessageReceivedEvent}
     * @return currently playing {@link AudioTrack}
     */
    public AudioTrack getNpAudioTrack(MessageReceivedEvent event) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        final AudioPlayer audioPlayer = musicManager.AUDIOPLAYER;

        return audioPlayer.getPlayingTrack();

    }

}

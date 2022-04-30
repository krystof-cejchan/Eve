package commands.textCommands;

import _library_class.LibraryClass;
import audio_player.GuildMusicManager;
import audio_player.NowPlayingCommand;
import audio_player.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import commands.ICommands;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * <p style="background-color:Green; color:Black">This command fast-forwards current track (in seconds)</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _FF implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {


        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args.length > 1) {
            int ff = Integer.parseInt(args[1]) * 1000;
            @Nullable
            AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

            @Nullable
            AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

            try {
                if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {

                    assert connectedChannel != null;
                    if (connectedChannel.equals(connectedChannelSelf)) {

                        final GuildMusicManager musicManager = PlayerManager.getInstance()
                                .getMusicManager(event.getGuild());
                        final AudioPlayer audioPlayer = musicManager.AUDIOPLAYER;
                        if (audioPlayer.getPlayingTrack() != null) {
                            long dur = audioPlayer.getPlayingTrack().getDuration();
                            int pos = (int) audioPlayer.getPlayingTrack().getPosition();
                            if (audioPlayer.getPlayingTrack().getDuration()
                                    > audioPlayer.getPlayingTrack().getPosition() + ff)// if statement
                            {
                                audioPlayer.getPlayingTrack()
                                        .setPosition(audioPlayer.getPlayingTrack().getPosition() + ff);

                                event.getChannel()
                                        .sendMessage("FastForwarding from __" + NowPlayingCommand.getTimestamp(pos)
                                                + "__ → __" + NowPlayingCommand.getTimestamp(pos + ff)
                                                + "__  \nSong duration: " + NowPlayingCommand.getTimestamp(dur))
                                        .queue();
                                LibraryClass.addReactionToTheMsg(event, "U+23E9");
                            } else {
                                event.getChannel().sendMessage("Song duration exceeded").queue();

                            }

                        }

                    }
                }
            } catch (Exception ignored) {

            }
        }

    }

    @Override
    public String getName() {

        return "Fast-Forward the current track";
    }

    @Override
    public String whatDoIDo() {

        return "This command fast-forwards current track (in seconds)";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("ff");
        t.add("fastforward");
        return t;
    }


}

package audio_player;

import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import voice_and_listening.Echo;

import javax.annotation.Nullable;
import java.util.Objects;

public class VolumeCommand {
    Echo echo = new Echo();
    private int volumeBeforeMuted = 0;

    public void setVolume(MessageReceivedEvent event, int volume) {
        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {

                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {
                    if (volume > 200 || volume < 0) {

                        event.getMessage().reply("Please, provide Volume value within range from 0 to 200").queue();
                        return;
                    }
                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                    event.getChannel().sendMessage("Volume: *" + musicManager.AUDIOPLAYER.getVolume() + " → " + volume + "*").queue();
                    musicManager.AUDIOPLAYER.setVolume(volume);

                    // echoVolume
                    echo.setVolume(volume);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void upDownVolume(MessageReceivedEvent event, String UPorDOWN) {
        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
                // uživatel někde je a bot taky
                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {
                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                    int volumenow = musicManager.AUDIOPLAYER.getVolume();
                    if (Objects.equals(UPorDOWN, "UP")) {
                        if (volumenow < 190) {
                            event.getChannel().sendMessage("Vol: *" + volumenow + " → " + volumenow + 10 + "*").queue();
                            musicManager.AUDIOPLAYER.setVolume(volumenow + 10);
                            return;
                        }

                    }

                    if (Objects.equals(UPorDOWN, "DOWN")) {
                        if (volumenow < 10) {
                            musicManager.AUDIOPLAYER.setVolume(volumenow - 10);
                        }

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mute(MessageReceivedEvent event) {
        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
                // uživatel někde je a bot taky
                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {
                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                    volumeBeforeMuted = musicManager.AUDIOPLAYER.getVolume();
                    event.getChannel().sendMessage("🔇: *" + musicManager.AUDIOPLAYER.getVolume() + " → 0*").queue();
                    musicManager.AUDIOPLAYER.setVolume(0);

                    // echoVolume
                    echo.setVolume(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unmute(MessageReceivedEvent event) {
        @Nullable AudioChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel(); // user

        @Nullable AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot

        try {
            if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
                // uživatel někde je a bot taky
                assert connectedChannel != null;
                if (connectedChannel.equals(connectedChannelSelf)) {
                    final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
                    event.getChannel().sendMessage("Vol: *" + musicManager.AUDIOPLAYER.getVolume() + " → " + volumeBeforeMuted + "*").queue();
                    musicManager.AUDIOPLAYER.setVolume(volumeBeforeMuted);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCurrentVolume(MessageReceivedEvent event) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        return musicManager.AUDIOPLAYER.getVolume();
    }

}
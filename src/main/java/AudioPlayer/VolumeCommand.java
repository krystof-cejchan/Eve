package AudioPlayer;

import javax.annotation.Nullable;

import Speech_Texts_Listening.Echo;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class VolumeCommand {
	Echo echo = new Echo();

	public void setVolume(MessageReceivedEvent event, int volume) {
		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		try {
			if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
				// uživatel někde je a bot taky
				if (connectedChannel.equals(connectedChannelSelf)) {
					final GuildMusicManager musicManager = PlayerManager.getInstance()
							.getMusicManager(event.getGuild());
					event.getChannel()
							.sendMessage("Volume: *" + musicManager.AUDIOPLAYER.getVolume() + " → " + volume + "*")
							.queue();
					musicManager.AUDIOPLAYER.setVolume(volume);

					// echoVolume
					echo.setVolume(volume);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void upDownVolume(MessageReceivedEvent event, String UPorDOWN) {
		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		try {
			if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
				// uživatel někde je a bot taky
				if (connectedChannel.equals(connectedChannelSelf)) {
					final GuildMusicManager musicManager = PlayerManager.getInstance()
							.getMusicManager(event.getGuild());
					int volumenow = musicManager.AUDIOPLAYER.getVolume();
					if (UPorDOWN == "UP") {
						if (volumenow < 190) {
							event.getChannel().sendMessage("Vol: *" + volumenow + " → " + volumenow + 10 + "*").queue();
							musicManager.AUDIOPLAYER.setVolume(volumenow + 10);
							return;
						}

					}

					if (UPorDOWN == "DOWN") {
						if (volumenow < 10) {
							musicManager.AUDIOPLAYER.setVolume(volumenow - 10);
							return;
						}

					}

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void mute(MessageReceivedEvent event) {
		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		try {
			if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
				// uživatel někde je a bot taky
				if (connectedChannel.equals(connectedChannelSelf)) {
					final GuildMusicManager musicManager = PlayerManager.getInstance()
							.getMusicManager(event.getGuild());
					event.getChannel().sendMessage("🔇: *" + musicManager.AUDIOPLAYER.getVolume() + " → 0*").queue();
					musicManager.AUDIOPLAYER.setVolume(0);

					// echoVolume
					echo.setVolume(0);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void unmute(MessageReceivedEvent event) {
		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		try {
			if (!(connectedChannel == (null)) || !(connectedChannelSelf == (null))) {
				// uživatel někde je a bot taky
				if (connectedChannel.equals(connectedChannelSelf)) {
					final GuildMusicManager musicManager = PlayerManager.getInstance()
							.getMusicManager(event.getGuild());
					event.getChannel().sendMessage("Vol: *" + musicManager.AUDIOPLAYER.getVolume() + " → 10*").queue();
					musicManager.AUDIOPLAYER.setVolume(10);

				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
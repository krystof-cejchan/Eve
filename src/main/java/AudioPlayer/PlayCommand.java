package AudioPlayer;

import java.util.regex.Matcher;
//import tk.ardentbot.main.Ardent.spotifyApi;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import Main.VoiceChannels;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PlayCommand {

	public void playMusic(MessageReceivedEvent event, String url, boolean isLink) {
		final MessageChannel channel = event.getChannel();

		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		VoiceChannels vc = new VoiceChannels();

		if (!(connectedChannel == (null))) {
			if (connectedChannel.equals(connectedChannelSelf)) {
				if (isLink) {

					if (url.contains("spotify.com")) {
// finish
					} else
						loadNPlay(channel, url, event);
				} else {
					loadNPlay(channel, "ytsearch:" + url, event);
				}

			} else {

				vc.Join(event);
				if (isLink) {
					loadNPlay(channel, url, event);
				} else {
					loadNPlay(channel, "ytsearch:" + url, event);
				}

			}
		}

		else {
			event.getMessage().reply("where r u? 🥺").queue();
		}

	}

	protected void loadNPlay(MessageChannel channel, String url, MessageReceivedEvent event) {
		PlayerManager.getInstance().loadAndPlay(channel, url, false, event);
	}

}

}

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
		/*
		 * final Member self = (Member) event.getGuild().getSelfMember(); final
		 * GuildVoiceState selfVoiceState = (GuildVoiceState) self.getVoiceState();
		 */

		@Nullable
		AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel(); // user

		@Nullable
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot

		VoiceChannels vc = new VoiceChannels();

		if (!(connectedChannel == (null))) {
			// uživatel někde je
			if (connectedChannel.equals(connectedChannelSelf)) {
				// uživatel je s botem a proto se hraje hudba
				if (isLink) {
					
					if (url.contains("spotify.com")) {/*
			            String[] parsed = url.split("/track/");
			            if (parsed.length == 2) {
			                final TrackRequest request = spotifyApi.getTrack(parsed[1]).build();
			                try {
			                    trackUrl = request.get().getName();
			                }
			                catch (Exception e) {
			                    new BotException(e);
			                } }
			          */  }
					else
					loadNPlay(channel, url, event);
				} else {
					loadNPlay(channel, "ytsearch:" + url, event);
				}

			} else {
				// uživatel není s botem, proto ho joini a hraj hudbu

				vc.Join(event);
				if (isLink) {
					loadNPlay(channel, url, event);
				} else {
					loadNPlay(channel, "ytsearch:" + url, event);
				}

			}
		}

		else {
			event.getMessage().reply("where r u?  🥺").queue();
		}

	}

	protected void loadNPlay(MessageChannel channel, String url, MessageReceivedEvent event) {
		PlayerManager.getInstance().loadAndPlay(channel, url, false, event);
	}

	public boolean isLink(String link) {
		String urlRegex = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
		Pattern pattern = Pattern.compile(urlRegex);
		Matcher matcher = pattern.matcher(link);
		return matcher.find();

	}

}

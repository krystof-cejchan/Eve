package AudioPlayer;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PlayerManager {

	private static PlayerManager INSTANCE;

	private final Map<Long, GuildMusicManager> MUSICMANAGERS;
	private final AudioPlayerManager AUDIOPLAYERMANAGER;

	public PlayerManager() {
		this.MUSICMANAGERS = new HashedMap<>();
		this.AUDIOPLAYERMANAGER = new DefaultAudioPlayerManager();

		AudioSourceManagers.registerRemoteSources(AUDIOPLAYERMANAGER);
		AudioSourceManagers.registerLocalSource(AUDIOPLAYERMANAGER);
	}

	public GuildMusicManager getMusicManager(net.dv8tion.jda.api.entities.Guild guild) {
		return this.MUSICMANAGERS.computeIfAbsent(guild.getIdLong(), (guildId) -> {
			final GuildMusicManager guildMusicManager = new GuildMusicManager(AUDIOPLAYERMANAGER);
			guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

			return guildMusicManager;
		});
	}

	public void loadAndPlay(MessageChannel channel, String url, boolean isQueue, MessageReceivedEvent event) {

		final GuildMusicManager musicManager = this.getMusicManager(event.getGuild());

		this.AUDIOPLAYERMANAGER.loadItemOrdered(musicManager, url, new AudioLoadResultHandler() {

			@Override
			public void trackLoaded(AudioTrack track) {
				musicManager.SCHEDULER.queue(track);

				event.getMessage().reply("```yaml\n" + "Adding to queue:").append(track.getInfo().title)
						.append("  #  from: " + track.getInfo().author + " channel```").queue();

			}

			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				// tracks jsou videa, která se vyhledaly z searchkey
				if (isQueue == false) {
					List<AudioTrack> tracks = playlist.getTracks();
					AudioTrack track = tracks.get(0);
					musicManager.SCHEDULER.queue(track);

					event.getMessage().reply("```yaml\n" + "Adding to queue:").append(track.getInfo().title)
							.append("  #  from: " + track.getInfo().author + " channel```").queue();
				} else {
					int pocet = 0;
					List<AudioTrack> tracks = playlist.getTracks();
					for (AudioTrack audioTrack : tracks) {
						musicManager.SCHEDULER.queue(audioTrack);
						pocet = pocet + 1;
					}
					channel.sendMessage("Successfully added: " + pocet +" tracks").queue();

				}

				///

				/*
				 * pokud se nahraje playlist, který se má přehrát celý, tak TO-DO
				 */

			}

			@Override
			public void noMatches() {
				channel.sendMessage("Nothing was found").queue();

			}

			@Override
			public void loadFailed(FriendlyException exception) {
				channel.sendMessage("Failed to load the track" + exception).queue();

			}
		});
	}

	public static PlayerManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new PlayerManager();
		}
		return INSTANCE;
	}

}

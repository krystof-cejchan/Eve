package audio_player;

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

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
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

	public void sendRegularMessage(MessageReceivedEvent event, AudioTrack track) {
		event.getMessage().reply("```yaml\n" + "Adding to queue:").append(track.getInfo().title)
				.append("  #  from: " + track.getInfo().author + " channel```").queue();
	}

	public MessageEmbed createEmbedMsg(AudioTrack track, String usersInput) {
		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setTitle("New Song has been Added");
		embedBuilder.addField("What I understood you:", usersInput, true);
		embedBuilder.addField("What I'm going to play:", track.getInfo().title, true);
		return embedBuilder.build();
	}

	public void loadAndPlay(MessageChannel channel, String url, boolean isQueue, MessageReceivedEvent event,
			MessageTypes msgType, String usersInput) {

		final GuildMusicManager musicManager = this.getMusicManager(event.getGuild());

		this.AUDIOPLAYERMANAGER.loadItemOrdered(musicManager, url, new AudioLoadResultHandler() {

			@Override
			public void trackLoaded(AudioTrack track) {
				musicManager.SCHEDULER.queue(track);

				switch (msgType) {
				case EMBED_MESSAGE:
					event.getChannel().sendMessageEmbeds(createEmbedMsg(track, usersInput));
					break;
				case REG_MESSAGE:
					sendRegularMessage(event, track);
					break;
				default:
					System.out.println("error while sending track info");
				}

			}

			@Override
			public void playlistLoaded(AudioPlaylist playlist) {

				if (isQueue) {
					int addedTracks_count = 0;
					List<AudioTrack> tracks = playlist.getTracks();
					for (AudioTrack audioTrack : tracks) {
						musicManager.SCHEDULER.queue(audioTrack);
						addedTracks_count = addedTracks_count + 1;
					}
					/*
					 * tracks.forEach(audioTrack -> { musicManager.SCHEDULER.queue(audioTrack);
					 * addedTracks_count = addedTracks_count + 1; });
					 */
					channel.sendMessage("Successfully added: " + addedTracks_count + " tracks").queue();

				} else {
					List<AudioTrack> tracks = playlist.getTracks();
					AudioTrack track = tracks.get(0);
					musicManager.SCHEDULER.queue(track);

					event.getMessage().reply("```yaml\n" + "Adding to queue:").append(track.getInfo().title)
							.append("  #  from: " + track.getInfo().author + " channel```").queue();
				}

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

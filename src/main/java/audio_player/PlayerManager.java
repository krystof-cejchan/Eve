package audio_player;

import _library_class.DiscordRelated_LibraryClass;
import _library_class.LibraryClass;
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
import org.apache.commons.collections4.map.HashedMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    /**
     * Singleton design pattern
     *
     * @return instance of the class, if any other instance does not exist
     */
    public static PlayerManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

    public GuildMusicManager getMusicManager(net.dv8tion.jda.api.entities.Guild guild) {
        return this.MUSICMANAGERS.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(AUDIOPLAYERMANAGER);
            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());

            return guildMusicManager;
        });
    }

    /**
     * Sends regular message to the text channel as a reply with the new track info
     *
     * @param event {@link MessageReceivedEvent}
     * @param track new track to be added to the queue
     */
    public void sendRegularMessage(MessageReceivedEvent event, AudioTrack track) {
        event.getMessage().reply("```yaml\n" + "Adding to the queue:").append(track.getInfo().title).append("  #  from: ").append(track.getInfo().author).append(" channel```").queue();
    }

    /**
     * Creates {@link MessageEmbed} with information regarding user's voice input
     *
     * @param track        {@link AudioTrack}
     * @param usersInput   user's voice input transcribed to text {@link voice.voice_and_listening.SpeechToText}
     * @param event        {@link MessageReceivedEvent}
     * @param searchingfor what is bot searching for in youtube (takes usersInput and removes words that are not meant to be involved in the song title ie. "play","by"...)
     * @return MessageEmbed containing song info and user's input data
     * @throws IllegalArgumentException if arguments are incorrect
     * @author krystof-cejchan
     */
    public MessageEmbed createEmbedMsg(AudioTrack track, String usersInput, MessageReceivedEvent event, String searchingfor) throws IllegalArgumentException {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription("A new song request");
        embedBuilder.setAuthor(Objects.requireNonNull(event.getGuild().getMember(event.getAuthor())).getNickname(), null, event.getAuthor().getAvatarUrl());
        embedBuilder.addField("What I understood you:", usersInput, true);
        embedBuilder.addField("What I'm searching for:", searchingfor.replace("ytsearch:", ""), true);
        embedBuilder.addField("What I'm going to play:", "***" + track.getInfo().title + "***", false);
        embedBuilder.setColor(LibraryClass.getRandomColor());
        return embedBuilder.build();
    }

    /**
     * Loads and plays song(s), depending on {@code isQueue}
     *
     * @param channel    {@link MessageChannel}
     * @param url        url or search text
     * @param isQueue    if true, loads all song from link and adds them all to the queue; if false, it loads only one song <hr>
     *                   <i>(for instance, if user passes a playlist as a arguments when triggering {@link PlayCommand} but isQueue is false,
     *                   then only the first song from the playlist will be added to the queue, otherwise all songs will be added)</i><hr>
     * @param event      {@link MessageReceivedEvent}
     * @param msgType    {@link MessageTypes}
     * @param usersInput user's input
     * @author krystof-cejchan
     */
    public void loadAndPlay(MessageChannel channel, String url, boolean isQueue, MessageReceivedEvent event, MessageTypes msgType, String usersInput) {

        final GuildMusicManager musicManager = this.getMusicManager(event.getGuild());

        this.AUDIOPLAYERMANAGER.loadItemOrdered(musicManager, url, new AudioLoadResultHandler() {

            /**
             * single track loaded
             * @param track {@link AudioTrack} which is supposed to be adde to the queue
             */
            @Override
            public void trackLoaded(AudioTrack track) {
                musicManager.SCHEDULER.queue(track);
                switch (msgType) {
                    case EMBED_MESSAGE:
                        event.getChannel().sendMessageEmbeds(createEmbedMsg(track, usersInput, event, url)).queue();
                        break;
                    case REG_MESSAGE:
                        sendRegularMessage(event, track);
                        break;
                    default:
                        try {
                            sendRegularMessage(event, track);
                            break;
                        } catch (Exception e) {
                            event.getChannel().sendMessage("There's been an error").queue();
                        }
                }

            }

            /**
             * playlist loaded
             * @param playlist a list of {@link AudioTrack}-s
             */
            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

                List<AudioTrack> tracks = playlist.getTracks();
                if (isQueue) {

                    /* for (AudioTrack audioTrack : tracks) {
                        musicManager.SCHEDULER.queue(audioTrack);

                    }*/

                    tracks.forEach(musicManager.SCHEDULER::queue);

                    channel.sendMessage("Successfully added: " + playlist.getTracks().size() + " tracks").queue();

                } else {
                    AudioTrack track = tracks.get(0);
                    musicManager.SCHEDULER.queue(track);
                    switch (msgType) {
                        case EMBED_MESSAGE:
                            event.getChannel().sendMessageEmbeds(createEmbedMsg(track, usersInput, event, url)).queue();
                            break;
                        case REG_MESSAGE:
                            sendRegularMessage(event, track);
                            break;
                        default:
                            try {
                                sendRegularMessage(event, track);
                                break;
                            } catch (Exception e) {
                                event.getChannel().sendMessage("There's been an error").queue();
                            }
                    }
                }
            }

            /**
             * no track with this title or upload under this url was found
             */
            @Override
            public void noMatches() {
                channel.sendMessage("Nothing was found for: __" + usersInput + "__").queue();

            }

            /**
             * track could not be loaded
             * @param exception {@link FriendlyException} this exception will be printed out to the user as an error message
             */
            @Override
            public void loadFailed(FriendlyException exception) {
                channel.sendMessage("Failed to load the track" + exception + "\nTry again later.\uD83D\uDE1F\uD83D\uDE1F").queue();
                DiscordRelated_LibraryClass.sendDevMsg(event, "load song failed - msg: " + event.getMessage().getContentRaw(), false);

            }

        });
    }

}

package audioplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import enums_annotations_exceptions.enums.MessageTypes;
import enums_annotations_exceptions.exceptions.NoTrackMatchException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.collections4.map.HashedMap;
import org.jetbrains.annotations.NotNull;
import utility_class.SendPrivateMsgToDev;
import utility_class.UtilityClass;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

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
        event.getMessage().reply("```yaml\n" + "Adding to the queue:").append(track.getInfo().title)
                .append("  #  from: ").append(track.getInfo().author).append(" channel```").queue();
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
    public MessageEmbed createEmbedMsg(AudioTrack track, String usersInput, MessageReceivedEvent event,
                                       String searchingfor) throws IllegalArgumentException {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription("A new song request");
        embedBuilder.setAuthor(Objects.requireNonNull(event.getGuild().getMember(event.getAuthor())).getNickname(),
                null, event.getAuthor().getAvatarUrl());
        embedBuilder.addField("What I understood you:", usersInput, true);
        embedBuilder.addField("What I'm searching for:", searchingfor.replace("ytsearch:", ""),
                true);
        embedBuilder.addField("What I'm going to play:", "***" + track.getInfo().title + "***", false);
        embedBuilder.setColor(UtilityClass.getRandomColor());
        return embedBuilder.build();
    }

    /**
     * Loads and plays song(s), depending on {@code isQueue}
     *
     * @param channel         {@link MessageChannel}
     * @param url             url or search text
     * @param isQueue         if true, loads all song from link and adds them all to the queue; if false, it loads only one song <hr>
     *                        <i>(for instance, if user passes a playlist as a arguments when triggering {@link PlayCommand} but isQueue is false,
     *                        then only the first song from the playlist will be added to the queue, otherwise all songs will be added)</i><hr>
     * @param event1          {@link MessageReceivedEvent}
     * @param event2          {@link SlashCommandInteractionEvent}
     * @param msgType         {@link MessageTypes}
     * @param usersInput      user's input
     * @param playImmediately whether song should be played immediately
     * @param multiplyAdded   means that no msgs will be sent
     * @author krystof-cejchan
     */
    public void loadAndPlay(MessageChannel channel, String url, boolean isQueue, @Nullable MessageReceivedEvent event1,
                            @Nullable SlashCommandInteractionEvent event2, @Nullable MessageTypes msgType, String usersInput,
                            boolean playImmediately, boolean multiplyAdded) {
        if (event1 != null && event2 == null) {
            final GuildMusicManager musicManager = this.getMusicManager(event1.getGuild());

            this.AUDIOPLAYERMANAGER.loadItemOrdered(musicManager, url, new AudioLoadResultHandler() {

                /**
                 * single track loaded
                 *
                 * @param track {@link AudioTrack} which is supposed to be added to the queue
                 */
                @Override
                public void trackLoaded(AudioTrack track) {
                    musicManager.SCHEDULER.queue(track);
                    if (!multiplyAdded) {
                        switch (Objects.requireNonNull(msgType)) {
                            case EMBED_MESSAGE:
                                event1.getChannel().sendMessageEmbeds(createEmbedMsg(track, usersInput, event1, url)).queue();
                                break;
                            case REG_MESSAGE:
                                sendRegularMessage(event1, track);
                                break;
                            default:
                                try {
                                    sendRegularMessage(event1, track);
                                    break;
                                } catch (Exception e) {
                                    event1.getChannel().sendMessage("There's been an error").queue();
                                }
                        }
                    }
                }

                /**
                 * playlist loaded
                 *
                 * @param playlist a list of {@link AudioTrack}s
                 */
                @Override
                public void playlistLoaded(AudioPlaylist playlist) {

                    List<AudioTrack> tracks = playlist.getTracks();
                    if (isQueue) {
                        tracks.forEach(musicManager.SCHEDULER::queue);
                        if (!multiplyAdded)
                            channel.sendMessage("Successfully added: " + playlist.getTracks().size() + " tracks").queue();
                    } else {
                        AudioTrack track = tracks.get(0);
                        musicManager.SCHEDULER.queue(track);
                        if (!multiplyAdded) {
                            switch (Objects.requireNonNull(msgType)) {
                                case EMBED_MESSAGE:
                                    event1.getChannel().sendMessageEmbeds(createEmbedMsg(track, usersInput, event1, url)).queue();
                                    break;
                                case REG_MESSAGE:
                                    sendRegularMessage(event1, track);
                                    break;
                                default:
                                    try {
                                        sendRegularMessage(event1, track);
                                        break;
                                    } catch (Exception e) {
                                        event1.getChannel().sendMessage("There's been an error").queue();
                                    }
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
                    SendPrivateMsgToDev.sendDevMsg(event1.getAuthor(), "load song failed - msg: " + event1.getMessage().getContentRaw(), false);

                }

            });
        }

        if (event1 == null && event2 != null) {//Slash command

            final GuildMusicManager musicManager = this.getMusicManager(Objects.requireNonNull(event2.getGuild()));

            this.AUDIOPLAYERMANAGER.loadItemOrdered(musicManager, url, new AudioLoadResultHandler() {

                /**
                 * single track loaded
                 *
                 * @param track {@link AudioTrack} which is supposed to be added to the queue
                 */
                @Override
                public void trackLoaded(AudioTrack track) {
                    musicManager.SCHEDULER.queue(track);
                    //event2.reply("**" + track.getInfo().title + "** was successfully added").queue();
                    if (!multiplyAdded)
                        event2.replyEmbeds(newSongAddedThroughSlash(Objects.requireNonNull(event2.getMember()),
                                track, musicManager.SCHEDULER.QUEUE).build()).queue();
                }

                /**
                 * playlist loaded
                 *
                 * @param playlist a list of {@link AudioTrack}-s
                 */
                @Override
                public void playlistLoaded(AudioPlaylist playlist) {
                    List<AudioTrack> tracks = playlist.getTracks();
                    List<Long> tracksDuration = new ArrayList<>();
                    tracks.forEach(track -> tracksDuration.add(track.getDuration()));
                    long sumOfTracksDuration = 0;
                    for (Long dur : tracksDuration)
                        sumOfTracksDuration += dur;


                    if (isQueue) {
                        StringBuilder stringBuilder =
                                new StringBuilder("```diff\nPlaylist title: " + playlist.getName() + "\n+ " + tracks.size() +
                                        " [Duration: " + UtilityClass.getTimeStampMilliToStringTime(sumOfTracksDuration)
                                        + "] tracks were successfully added\n```");
                        if (!playImmediately)
                            tracks.forEach(musicManager.SCHEDULER::queue);
                        else {
                            List<AudioTrack> q = new ArrayList<>(musicManager.SCHEDULER.QUEUE);
                            for (int i = 0; i < tracks.size(); i++)
                                q.add(i, tracks.get(i));

                            musicManager.SCHEDULER.QUEUE.clear();
                            musicManager.SCHEDULER.QUEUE.addAll(q);
                            if (musicManager.AUDIOPLAYER.getPlayingTrack() != tracks.get(0))
                                musicManager.SCHEDULER.nextTrack();

                        }
                        if (!multiplyAdded) {
                            (playImmediately
                                    ?
                                    event2.reply(stringBuilder.append(" and moved before all other tracks").toString())
                                    :
                                    event2.reply(stringBuilder.toString())
                            ).queue();
                        }
                    } else {
                        AudioTrack track = tracks.get(0);

                        if (playImmediately) {
                            List<AudioTrack> q = new ArrayList<>(musicManager.SCHEDULER.QUEUE);
                            q.add(0, track);
                            musicManager.SCHEDULER.QUEUE.clear();
                            musicManager.SCHEDULER.QUEUE.addAll(q);
                            if (musicManager.AUDIOPLAYER.getPlayingTrack() != track)
                                musicManager.SCHEDULER.nextTrack();

                        } else
                            musicManager.SCHEDULER.queue(track);
                        if (!multiplyAdded) {
                            event2.replyEmbeds(newSongAddedThroughSlash(Objects.requireNonNull(event2.getMember()),
                                    track, musicManager.SCHEDULER.QUEUE).build()).queue();
                        }
                    }
                }

                /**
                 * no track with this title or upload under this url was found
                 */
                @Override
                public void noMatches() {
                    try {
                        event2.reply("Nothing matches your input :(").queue();
                        throw new NoTrackMatchException("nothing matches");
                    } catch (NoTrackMatchException e) {
                        e.printStackTrace();
                    }

                }

                /**
                 * track could not be loaded
                 * @param exception {@link FriendlyException} this exception will be printed out to the user as an error message
                 */
                @Override
                public void loadFailed(FriendlyException exception) {
                    event2.reply("There has been an error while loading your track").queue();
                    SendPrivateMsgToDev.sendDevMsg(event2.getUser(), "load song failed - msg: " + event2.getCommandString(),
                            false);
                    exception.printStackTrace();

                }

            });
        }

    }

    /**
     * builds an embed message which is ready to be built and sent {@code EmbedBuilder.build()} {@link EmbedBuilder}
     *
     * @param author                 user who triggered the command
     * @param audioTrackToBeAddedToQ {@link AudioTrack} which the user searched for
     * @param queue                  current queue
     * @return EmbedBuilder to be sent to the text channel from which command was triggered
     */
    private EmbedBuilder newSongAddedThroughSlash(@NotNull Member author, @NotNull AudioTrack audioTrackToBeAddedToQ,
                                                  @NotNull BlockingQueue<AudioTrack> queue) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(UtilityClass.getRandomColor())
                /* .setAuthor(author.getNickname(),
                         "", author.getAvatarUrl())*/
                .setTitle("New Track has been added to the Queue:")
                .addField("Title", audioTrackToBeAddedToQ.getInfo().title, true)
                .addField("Author/Channel", audioTrackToBeAddedToQ.getInfo().author, true)
                .addField("TimeLine:", "Length " + (UtilityClass.getTimeStampMilliToStringTime(audioTrackToBeAddedToQ
                        .getDuration())), false)
                .addField("Source URL", "[Link to the source](" + audioTrackToBeAddedToQ.getInfo().uri + ")",
                        true)
                .setFooter(author.getUser().getAsTag(), author.getEffectiveAvatarUrl());

        return (
                (String.valueOf(new ArrayList<>(queue).indexOf(audioTrackToBeAddedToQ)).isEmpty()
                        ? builder
                        : builder
                        .addField("Position", new ArrayList<>(queue).indexOf(audioTrackToBeAddedToQ) + 1
                                        + ". in the queue",
                                true))
        );
    }
}






package cz.krystofcejchan.audioplayer;

import cz.krystofcejchan.commands.purecommands.subparts.GetCurrentTrack;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * when starting new song, bot will let users know about it
 */
public class LetUsersKnowOfPlayingNextTrack {
    /**
     * writes to the text channel about playing next song
     *
     * @param event {@link MessageReceivedEvent}
     */
    public static void informAboutPlayingNextTrack(@NotNull MessageReceivedEvent event) {
        /*event.getChannel().sendMessage("Playing next song: **" + new NowPlayingCommand()
                .getNpAudioTrack(event).getInfo().title + "**").queue();*/
        event.getChannel().sendMessageEmbeds(new EmbedBuilder().setColor(UtilityClass.getRandomColor())
                .setTitle("Playing the following song.").addField(Objects.requireNonNull(GetCurrentTrack
                                .getTrack(event.getGuild())).getInfo().title,
                        "by: *" + Objects.requireNonNull(GetCurrentTrack.getTrack(event.getGuild())).getInfo()
                                .author + "*", false).build()).queue();

    }
}

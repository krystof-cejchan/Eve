package audio_player;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * when starting new song, bot will let users know about it
 */
public class LetUsersKnowOfPlayingNextTrack {
    /**
     * writes to the text channel about playing next song
     *
     * @param event {@link MessageReceivedEvent}
     */
    public static void informAboutPlayingNextTrack(MessageReceivedEvent event) {
        event.getChannel().sendMessage("Playing next song: **" + new NowPlayingCommand().getNpAudioTrack(event).getInfo().title + "**").queue();
    }
}

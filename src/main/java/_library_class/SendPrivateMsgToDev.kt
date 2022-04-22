package _library_class;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Contains only discord related stuff
 * This Class serves as a Library Class according to the design patterns in Java
 * All methods in this class are defined as static, so that they can be called
 * without creating an instance of this class
 *
 * @author krystof-cejchan
 */
public class DiscordRelated_LibraryClass {
    /**
     * sends a message to the developer
     *
     * @param event {@link MessageReceivedEvent}
     */
    public static void sendDevMsg(MessageReceivedEvent event, String msg, boolean auth) {
        if (auth) {
            if (event.getAuthor().isBot())
                return;

        }
        User dev = event.getJDA().getUserById("348358747825111040");

        assert dev != null;
        dev.openPrivateChannel().flatMap(channel -> channel.sendMessage(msg)).queue();
    }
}

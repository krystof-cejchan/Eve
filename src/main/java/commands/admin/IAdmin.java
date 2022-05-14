package commands.admin;

import _library_class.GlobalValues;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Interface for admins' commands
 */
public interface IAdmin {
    static String usersOK() {
        return "348358747825111040";
    }

    static boolean isVerified(MessageReceivedEvent event) {
        return event.getAuthor().getId().equals(usersOK());
    }

    static String adminPrefix() {
        return GlobalValues.adminPrefix;
    }

    /**
     * SHOULD CONTAIN isVerified function
     *
     * @param event {@link MessageReceivedEvent}
     */
    void commitAdminOperation(MessageReceivedEvent event);

    String[] getTags();
}

package commands.admin;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utility_class.GlobalValues;

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
        return GlobalValues.ADMIN_PREFIX;
    }

    /**
     * SHOULD CONTAIN isVerified function
     *
     * @param event {@link MessageReceivedEvent}
     */
    void commitAdminOperation(MessageReceivedEvent event);

    String[] getTags();
}

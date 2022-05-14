package commands.admin;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HardShutDown implements IAdmin {
    @Override
    public void commitAdminOperation(MessageReceivedEvent event) {
        System.out.println(event.getAuthor() + " has triggered shutdown method");
        System.exit(0);
    }

    @Override
    public String[] getTags() {
        return new String[]{"HARDSHUTDOWN"};
    }
}

package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.commands.ICommands;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ContextException;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command sends the developers your message</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Support implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        try {
            StringBuilder a = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                a.append(" ").append(args[i]);
            }
            User dev = event.getJDA().getUserById("348358747825111040");

            assert dev != null;
            sendPrivateMessage(dev, "New Ticket: " + a + " by " + event.getAuthor().getAsTag());
            event.getMessage().addReaction("U+1F4E9").queue();
            event.getMessage().addReaction("U+1F4E8").queue();
            event.getMessage().addReaction("U+1F5F3").queue();

        } catch (ContextException e) {
            e.printStackTrace();
        }
    }

    protected void sendPrivateMessage(User user, String content) throws ContextException {

        if (user.isBot())
            return;


        user.openPrivateChannel().flatMap(channel -> channel.sendMessage(content)).queue();

    }

    @Override
    public String getName() {
        return "Support 24/7";
    }

    @Override
    public String whatDoIDo() {
        return "This command sends the developers your message";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("sup");
        t.add("support");

        return t;
    }


}

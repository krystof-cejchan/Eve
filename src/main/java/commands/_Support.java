package commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ContextException;

public class _Support implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");

		try {
			String a = "";
			for (int i = 1; i < args.length; i++) {
				a = a + " " + args[i];
			}
			User dev = event.getJDA().getUserById("348358747825111040");

			sendPrivateMessage(dev, "New Ticket: " + a + " by " + event.getAuthor().getAsTag());
			event.getMessage().addReaction("U+1F4E9").queue();
			event.getMessage().addReaction("U+1F4E8").queue();
			event.getMessage().addReaction("U+1F5F3").queue();

		} catch (ContextException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendPrivateMessage(User user, String content) throws ContextException {

		if (user.isBot())
			return;

		user.openPrivateChannel().flatMap(channel -> channel.sendMessage(content)).queue();

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Support 24/7";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command sends the developers your message";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("sup");
		t.add("support");

		return t;
	}

}

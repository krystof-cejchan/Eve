package commands_voice;

import java.util.ArrayList;
import java.util.Arrays;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Hello implements IListeningCommands {
	Guild guild;

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		event.getMessage().reply("Hello " + event.getAuthor().getAsTag()).queue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Hello";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command answers a simple hello";
	}

	@Override
	public ArrayList<String> getTags() {
		// TODO Auto-generated method stub
		ArrayList<String> tags = new ArrayList<>(Arrays.asList

		("hello", "hi", "hey"));

		return tags;
	}

}

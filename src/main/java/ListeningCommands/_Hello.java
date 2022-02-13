package ListeningCommands;

import java.util.ArrayList;
import java.util.Arrays;

import Main.CurrentTextChannel;
import net.dv8tion.jda.api.entities.Guild;

public class _Hello implements IListeningCommands {
	Guild guild;

	@Override
	public void doTask() {
		// TODO Auto-generated method stub

		guild.getTextChannelById(CurrentTextChannel.getId()).sendMessage("Hello").queue();
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

		("hello", "hi", "greetings", "hey"));

		return tags;
	}

}

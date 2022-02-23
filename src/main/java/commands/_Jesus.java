package commands;

import java.util.ArrayList;

import commands_others.Asian;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Jesus implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		Asian asian = new Asian();

		event.getMessage().reply(asian.sendJesus()).queue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "I will Send you to Jesus";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command sends you swiftly to Jesus";
	}

	@Override
	public ArrayList<String> getTriggers() {
		ArrayList<String> t = new ArrayList<>();
		t.add("jesus");
		t.add("js");

		return t;
	}

}

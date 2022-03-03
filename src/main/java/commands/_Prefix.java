package commands;

import java.util.ArrayList;

import main.Prefix;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Prefix implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		Prefix pref = new Prefix();

		try {
			if (/*
				 * event.getMessage().getContentRaw().split(" ")[1].contains(" ") == false &&
				 */ event.getMessage().getContentRaw().split(" ")[1].length() >= 1) {
				pref.setValue(event.getMessage().getContentRaw().split(" ")[1]);

				event.getMessage().reply("Prefix has been changed to " + pref.getValue()).queue();
			}
		} catch (Exception e) {
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Change Prefix";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command changes the default prefix";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("prefix");
		t.add("pf");
		return t;
	}

}

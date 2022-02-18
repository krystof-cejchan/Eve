package Commands;

import java.util.ArrayList;

import LIBRARYclass.LibraryClass;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Pp implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");

		int len = LibraryClass.generateRandomInt(-1, 50);
		if (args.length > 1) {
		} else {

			event.getMessage().reply("pp = " + len + "cm.").queue();
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Weenie";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command guesses your \"length\"";
	}

	@Override
	public ArrayList<String> getTriggers() {
		ArrayList<String> t = new ArrayList<>();
		t.add("pp");
		t.add("dick");
		t.add("weenie");
		return t;
	}

}

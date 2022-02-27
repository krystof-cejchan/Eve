package commands;

import java.util.ArrayList;

import _library_class.LibraryClass;
import main.Prefix;
import net.dv8tion.jda.api.EmbedBuilder;
//import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Help implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {

		EmbedBuilder embedBuilder = new EmbedBuilder();
		Prefix pref = new Prefix();

		embedBuilder.clear();
		int maxEmbedSize = 25;

		int cmdSize = CommandManager.getAllCommands().size();
		embedBuilder.clear();
		if (cmdSize > maxEmbedSize) {

			for (int i = 0; i < cmdSize; i++) {
				for (int j = 0; j < Math.abs(i * cmdSize - (Math.abs(i * cmdSize - maxEmbedSize))); j++) {
					for (int w = (i) * 25; w < cmdSize; w++) {
						String allTriggers = "";
						for (String trigger : CommandManager.getCommandbyId(w).getTriggers()) {
							allTriggers += pref.getValue() + trigger + "   ";
						}
						embedBuilder.addField("**" + CommandManager.getCommandbyId(w).getName() + "**  :  ",
								CommandManager.getCommandbyId(w).whatDoIDo() + "\n" + allTriggers, true);
					}
				}
				embedBuilder.setColor(LibraryClass.getRandomColor());
				embedBuilder.setTitle("All " + CommandManager.getAllCommands().size() + " commands:");
				event.getMessage().replyEmbeds(embedBuilder.build()).queue();
				embedBuilder.clear();
			}
		} else {

			for (ICommands iCommands : CommandManager.getAllCommands()) {
				String allTriggers = "";
				for (String trigger : iCommands.getTriggers()) {
					allTriggers += pref.getValue() + trigger + "   ";
				}

				embedBuilder.addField("**" + iCommands.getName() + "**  :  ",
						iCommands.whatDoIDo() + "\n" + allTriggers, true);

			}
			embedBuilder.setColor(LibraryClass.getRandomColor());
			embedBuilder.setTitle("All " + CommandManager.getAllCommands().size() + " commands:");
			event.getMessage().replyEmbeds(embedBuilder.build()).queue();
			embedBuilder.clear();
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Help";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command sends you a list of all commands";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("help");

		return t;
	}

}

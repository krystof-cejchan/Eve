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
//		opakuj odkud tam budou furt zbývat celé 25, potom vypiš zbatek,
//		pokud není nad 25, tak to vypiš vše rovnou
		if (CommandManager.getAllCommands().size() > maxEmbedSize) {

			for (int i = 0; i < CommandManager.getAllCommands().size() % maxEmbedSize; i++) {
				for (int j = 0; j < maxEmbedSize; j++) {
					int helper = j;
					ICommands iCommands = CommandManager.getCommandbyId(i * maxEmbedSize + helper);
					String allTriggers = "";
					for (String trigger : iCommands.getTriggers()) {
						allTriggers += pref.getValue() + trigger + "   ";
					}
					embedBuilder.addField("**" + iCommands.getName() + "**  :  ",
							iCommands.whatDoIDo() + "\n" + allTriggers, true);
				}
				event.getMessage().replyEmbeds(embedBuilder.build()).queue();
				embedBuilder.clear();
			}
			for (int k = 0; k < (CommandManager.getAllCommands().size()
					- (25 * (CommandManager.getAllCommands().size() % maxEmbedSize))); k++) {
				ICommands iCommands = CommandManager
						.getCommandbyId(k + (maxEmbedSize * (CommandManager.getAllCommands().size() % maxEmbedSize)));
				String allTriggers = "";
				for (String trigger : iCommands.getTriggers()) {
					allTriggers += pref.getValue() + trigger + "   ";
				}
				embedBuilder.addField("**" + iCommands.getName() + "**  :  ",
						iCommands.whatDoIDo() + "\n" + allTriggers, true);
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

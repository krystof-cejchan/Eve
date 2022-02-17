package Commands;

import java.awt.Color;
import java.util.ArrayList;

import Main.Listener;
import Main.Prefix;
import net.dv8tion.jda.api.EmbedBuilder;
//import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Help implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {

		/*
		 * MessageBuilder msgBuilder = new MessageBuilder(); for (ICommands iCommands :
		 * CommandManager.getAllCommands()) { String allTriggers = ""; for (String
		 * trigger : iCommands.getTriggers()) { allTriggers += Listener.prefix + trigger
		 * + "   "; }
		 * 
		 * msgBuilder.append("**" + iCommands.getName() + "**  :  ");
		 * msgBuilder.append(iCommands.whatDoIDo()); msgBuilder.append(allTriggers +
		 * "\n"); }
		 * 
		 * event.getChannel().sendMessage(msgBuilder.build()).queue();
		 * msgBuilder.clear();
		 */
		EmbedBuilder embedBuilder = new EmbedBuilder();
		Prefix pref = new Prefix();
		for (ICommands iCommands : CommandManager.getAllCommands()) {
			String allTriggers = "";
			for (String trigger : iCommands.getTriggers()) {
				allTriggers += pref.getValue() + trigger + "   ";
			}

			embedBuilder.addField("**" + iCommands.getName() + "**  :  ", iCommands.whatDoIDo() + "\n" + allTriggers,
					false);

		}
		embedBuilder.setColor(new Color(0, 191, 255));
		embedBuilder.setTitle("All " + CommandManager.getAllCommands().size() + " commands:");
		event.getMessage().replyEmbeds(embedBuilder.build()).queue();
		embedBuilder.clear();

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

package commands;

import java.util.ArrayList;

import commands_others.Asian;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _EmotionalDamage implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		Asian asian = new Asian();

		event.getMessage().reply(asian.sendEmotionalDamage()).queue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Emotional Damage";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command sends you one quick emotional damage";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("ed");
		t.add("emotionaldamage");

		return t;
	}

}

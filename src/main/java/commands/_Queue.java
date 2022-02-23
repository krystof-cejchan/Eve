package commands;

import java.util.ArrayList;

import audio_player.QueueCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Queue implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		QueueCommand q = new QueueCommand();
		q.getQueue(event);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Queue";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command informs you about the upcoming tracks";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("q");
		t.add("queue");
		t.add("list");
		return t;
	}

}

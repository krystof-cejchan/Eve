package commands;

import java.util.ArrayList;

import audio_player.QueueCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Remove implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String[] args = event.getMessage().getContentRaw().split(" ");

		try {
			if (args.length > 1) {
				QueueCommand q = new QueueCommand();
				q.removeFromQueue(event, Integer.valueOf(args[1]) - 1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Remove a track";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command removes *x.* track from the queue";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("remove");
		t.add("del");
		t.add("delete");
		return t;
	}

}

package Commands;

import java.util.ArrayList;

import AudioPlayer.ShuffleCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Shuffle implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		ShuffleCommand shuffle = new ShuffleCommand();
		shuffle.getShuffle(event);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Shuffle";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command shuffles all tracks in the queue";
	}

	@Override
	public ArrayList<String> getTriggers() {
		ArrayList<String> t = new ArrayList<>();
		t.add("mix");
		t.add("shuffle");

		return t;
	}

}

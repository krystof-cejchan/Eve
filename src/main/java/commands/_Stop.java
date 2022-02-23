package commands;

import java.util.ArrayList;

import audio_player.StopCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Stop implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		try {
			StopCommand stop = new StopCommand();
			stop.stopMusic(event);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Stop the tracks";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command forces the bot to stop playing current song, delete the whole queue, but remain in the voice channel";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("stop");
		t.add("destroy");
		return t;
	}

}

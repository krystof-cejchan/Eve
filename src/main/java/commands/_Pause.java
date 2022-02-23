package commands;

import java.util.ArrayList;

import audio_player.StopCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Pause implements ICommands{

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		try {
			StopCommand stop = new StopCommand();
			stop.pauseMusic(event);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Pause a track";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command pauses the currently playing song";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("pause");
		return t;
	}

}

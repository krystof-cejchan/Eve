package commands;

import java.util.ArrayList;

import audio_player.VolumeCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Unmute implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		try {
			VolumeCommand volume = new VolumeCommand();
			volume.unmute(event);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Unmute";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command unmutes the bot";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("unmute");
		return t;
	}

}

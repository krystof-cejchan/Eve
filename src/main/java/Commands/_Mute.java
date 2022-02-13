package Commands;

import java.util.ArrayList;

import AudioPlayer.VolumeCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Mute implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		try {
			VolumeCommand volume = new VolumeCommand();
			volume.mute(event);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Mute";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command mutes the bot, but the currently playing song will carry on playing!";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("mute");
		return t;
	}

}

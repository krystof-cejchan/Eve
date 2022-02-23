package commands;

import java.util.ArrayList;

import audio_player.ResumeCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Resume implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		try {
			ResumeCommand resume = new ResumeCommand();
			resume.resumeMusic(event);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Resume current song, if paused";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command resumes the currently paused song";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("resume");
		t.add("continue");
		return t;
	}

}

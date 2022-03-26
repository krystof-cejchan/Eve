package commands;

import java.util.ArrayList;

import _library_class.LibraryClass;
import audio_player.StopCommand;
import main.VoiceChannels;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Leave implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		VoiceChannels leaveVC = new VoiceChannels();
		StopCommand stop = new StopCommand();
		stop.stopMusic(event);
		leaveVC.Leave(event);
		LibraryClass.addReactionToTheMsg(event, "U+1F44B");

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Leave a voice channel";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command forces the bot to leave your voice channel and discard the song queue";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("leave");
		t.add("exit");
		t.add("quit");
		return t;
	}

}

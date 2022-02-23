package commands;

import java.util.ArrayList;

import audio_player.PlayCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Play implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");

		PlayCommand playC = new PlayCommand();
		String urlNeboSearchKey = "";

		if (playC.isLink(args[1])) {
			playC.playMusic(event, args[1], true);

		} else {
			for (int i = 1; i < args.length; i++) {
				urlNeboSearchKey = urlNeboSearchKey + args[i] + " ";

			}
			playC.playMusic(event, urlNeboSearchKey, false);
		}
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Play a track";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command adds a song to the tracklist";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t =new ArrayList<>();
		t.add("play");
		t.add("p");
		return t;
	}

}

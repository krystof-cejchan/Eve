package commands;

import java.util.ArrayList;

import _library_class.LibraryClass;
import audio_player.PlayQCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _PlayQueue implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String[] args = event.getMessage().getContentRaw().split(" ");

		try {

			PlayQCommand pq = new PlayQCommand();

			if (LibraryClass.isLink(args[1])) {
				pq.playMusic(event, args[1], true);

			} else {
				event.getChannel().sendMessage("Please, provide a proper YouTube link").queue();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Play a Queue";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command adds every song from your playlist to the queue";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("playqueue");
		t.add("playq");
		t.add("pq");
		return t;
	}

}

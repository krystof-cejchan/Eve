package Commands;

import java.util.ArrayList;

import AudioPlayer.SkipCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _SkipTo implements ICommands{

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String[] args = event.getMessage().getContentRaw().split(" ");

		try {
			if (args.length > 1) {
				SkipCommand s = new SkipCommand();
				s.skipTrackTo(event, Integer.valueOf(args[1]));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Skip To *x.* song in the queue";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command skips to *x.* in the queue";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("skipto");
		t.add("getto");
		return t;
	}

}

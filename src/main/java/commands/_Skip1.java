package commands;

import java.util.ArrayList;

import audio_player.NowPlayingCommand;
import audio_player.SkipCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Skip1 implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String[] args = event.getMessage().getContentRaw().split(" ");
		try {
			SkipCommand skip = new SkipCommand();
			if (args.length > 1) {
				for (int i = 0; i < Integer.valueOf(args[1]); i++) {
					skip.skipTrack(event, false);
				}
				NowPlayingCommand n = new NowPlayingCommand();
				event.getChannel()
						.sendMessage("Successfully skipped to: \n**" + n.getNpAudioTrack(event).getInfo().title + "**")
						.queue();
			} else {

				skip.skipTrack(event, true);

			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Skip one song";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command skips one track in the queue";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("skip");
		t.add("next");
		return t;
	}

}

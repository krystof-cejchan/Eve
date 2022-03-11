package commands_voice;

import java.util.ArrayList;
import java.util.Arrays;

import audio_player.SkipCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Skip_toNext implements IListeningCommands {

	@Override
	public void doTask(MessageReceivedEvent event) throws Exception {
		// TODO Auto-generated method stub
		try {
			SkipCommand skip = new SkipCommand();

			skip.skipTrack(event, true);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Skip to the next song";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command skips one track in the queue";
	}

	@Override
	public ArrayList<String> getTags() {
		// TODO Auto-generated method stub

		return new ArrayList<String>(Arrays.asList

		("skip", "skip one song", "next song"));
	}

}

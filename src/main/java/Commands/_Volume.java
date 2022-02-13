package Commands;

import java.util.ArrayList;

import AudioPlayer.VolumeCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Volume implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String[] args = event.getMessage().getContentRaw().split(" ");

		try {
			VolumeCommand volume = new VolumeCommand();
			if (args.length > 1) {

				if (args[1].equalsIgnoreCase("up")) {

					volume.upDownVolume(event, "UP");
					return;
				}
				if (args[1].equalsIgnoreCase("down")) {

					volume.upDownVolume(event, "DOWN");
					return;
				} else {
					volume.setVolume(event, Integer.parseInt(args[1]));
					return;
				}

			} else {

				event.getMessage().reply("Current Volume is `" + volume.getCurrentVolume(event)
						+ "`.\nTo change Volume, use volume <number 1 - 200>").queue();
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Volume";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command changes current volume of the bot";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("vol");
		t.add("volume");
		return t;
	}

}

package commands;

import java.util.ArrayList;

import main.VoiceChannels;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Join implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String[] args = event.getMessage().getContentRaw().split(" ");

		try {
			VoiceChannels joinVC = new VoiceChannels();
			if (args.length > 1) {
				System.out.println("notempty");
				joinVC.JoinChannel(event, args[1].toString());

			} else {
				System.out.println("empty");
				joinVC.Join(event);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Join a voice channel";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command forces the bot to join a voice channel. Write the title of the voice channel, if you don't want the bot to join you.";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("join");
		return t;
	}

}

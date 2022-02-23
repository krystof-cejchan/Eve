package commands;

import java.util.ArrayList;

import main.GifSender;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Gif implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String[] args = event.getMessage().getContentRaw().split(" ");
		String searchKey = "";

		try {
			if (args.length > 1) {

				for (int i = 1; i < args.length; i++) {
					searchKey = searchKey + "%20" + args[i];
				}

			}

			else {

				searchKey = "null";

			}

			GifSender gifs = new GifSender();
			String zprava = gifs.call_me(searchKey, "2");

			event.getMessage().reply((zprava)).queue();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Gif Sender";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This Command sends you a gif depending on your keyword";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("g");
		t.add("gif");
		t.add("movingpicture");
		return t;
	}

}

package Commands;

import java.util.ArrayList;

import AudioPlayer.NowPlayingCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _NowPlaying implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		nowPlaying(event);

	}

	private void nowPlaying(MessageReceivedEvent event) {
		try {
			NowPlayingCommand np = new NowPlayingCommand();
			np.getNowPlayingTrack(event);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Now Playing";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command informs you about the currently playing track";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("np");
		t.add("nowplaying");
		return t;
	}

}

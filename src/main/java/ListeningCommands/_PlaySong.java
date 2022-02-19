package ListeningCommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import AudioPlayer.PlayCommand;
import Speech_Texts_Listening.SpeechToText;
import Speech_Texts_Listening.SpeechToText.Language;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * @author kryst
 * @see IListeningCommands
 * 
 */

public class _PlaySong implements IListeningCommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		String s = "";
		try {
			String text = SpeechToText.getText();
			String[] args = text.split(" ");
			if ((args == null) == false) {

				Process p = Runtime.getRuntime().exec(
						"py C:\\Users\\kryst\\git\\repository3\\discordbottest\\src\\main\\java\\External_Files\\GettheMostSuitableSong.py "
								+ extracttheSong(args));
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

				new BufferedReader(new InputStreamReader(p.getErrorStream()));

				String output = "";
				while ((s = stdInput.readLine()) != null) {
					output = s;
				}

				PlayCommand playCommand = new PlayCommand();
				playCommand.playMusic(event, output, false);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Play a Track";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command adds a track to the queue or plays it right away";
	}

	@Override
	public ArrayList<String> getTags() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("play");
		t.add("play song");
		t.add("song");
		t.add("track");

		return t;
	}

	/**
	 * this function tries to extract a song name from String[] @param args
	 * 
	 * @param args is what users said seperated with spaces
	 * @return a song if possible
	 */
	private String extracttheSong(String[] args) {

		return null;
	}

}

package commands_voice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import _library_class.LibraryClass;
import audio_player.PlayCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import uncategorized.*;
import voice_and_listening.SpeechToText;

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

				PlayCommand playCommand = new PlayCommand();
				event.getChannel().sendMessage("Searching for " + extracttheSong(args)).queue();
				playCommand.playMusic(event, extracttheSong(args), false);

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
	 * @param args is what users said separated with spaces
	 * @return a song if possible
	 */
	private String extracttheSong(String[] args) {
		ArrayList<String> searchWords = new ArrayList<>();
		HashMap<String, Integer> hMap = new HashMap<>();
		for (int i = 0; i < args.length; i++) {
			searchWords.add(args[i].toLowerCase());
		}
		for (String word : searchWords) {
			for (String forbidden : forbiddenWords()) {
				if (word == forbidden) {
					searchWords.remove(searchWords.indexOf(word));
				}
			}

		}
		AllArrayCombinations_Algorithm.getCombinations(searchWords).forEach((item) -> {
			String path = "C:\\Users\\vecer\\git\\Eve\\src\\main\\java\\External_Files\\GetSongSearchResult_s.py";
			String output = LibraryClass.runPyScript(path, item);
			hMap.put(item, Integer.valueOf(output));
		});
		// String suitableSong =
		// LibraryClass.getTheMostSuitableStringFromAHashMap(hMap);

		/*
		 * maybe run suitableSong through the py script to get the "correct" song
		 * title??
		 */

		// AllArrayCombinations_Algorithm.getCombinations(searchWords);
		return LibraryClass.getTheMostSuitableStringFromAHashMap(hMap);
	}

	private ArrayList<String> forbiddenWords() {
		ArrayList<String> forbidden = new ArrayList<>(Arrays.asList

		("play", "song"));

		return forbidden;

	}

}

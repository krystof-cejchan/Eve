package _library_class;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import commands_voice.IListeningCommands;
import commands_voice.ListeningCommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * 
 * This Class serves as a Library Class according to the design patterns in Java
 * All methods in this class are defined as static, so that they can be called
 * without creating an instance of this class
 */
public class LibraryClass {
	public static boolean compareTwoArrays(ArrayList<String> listA, ArrayList<String> listB) {
		// boolean found = false;

		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if (listA.get(i).equalsIgnoreCase(listB.get(j))) {
					return true;
				}

			}

		}

		return false;
	}

	@Nullable
	public static Integer whereAreTwoArraysTheSame(ArrayList<String> listA, ArrayList<String> listB) {
		// int where = 0;

		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if (listA.get(i).equalsIgnoreCase(listB.get(j))) {
					return j;
				}

			}

		}
		// returns index from the SECOND arraylist
		return null;

	}

	public static Color getRandomColor() {
		return (new Color(generateRandomInt(0, 255), generateRandomInt(0, 255), generateRandomInt(0, 255)));
	}

	public static int generateRandomInt(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;

	}

	public static double averageOfDoubleArray(ArrayList<Double> arrInput) {
		double temp = 0;
		for (int i = 0; i < arrInput.size(); i++) {
			temp += arrInput.get(i);
		}
		return temp / arrInput.size();
	}

	public static IListeningCommands isUserInputVerySimilarToTags(String input) {
		for (IListeningCommands I : ListeningCommandManager.getAllCommands()) {
			try {
				ArrayList<String> words = new ArrayList<>();
				for (String aWord : input.toLowerCase().split(" ")) {
					words.add(aWord);
				}
				if (compareTwoArrays(I.getTags(), words)) {
					return I;
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	public static IListeningCommands getTheMostSuitableItemFromAHashMap(HashMap<IListeningCommands, Double> map) {
		IListeningCommands ImostLikelyToBe = null;
		double highest = 0;

		for (IListeningCommands I : ListeningCommandManager.getAllCommands()) {
			if (map.get(I) > highest) {
				highest = map.get(I);
				ImostLikelyToBe = I;
			}
		}
		double minSimilarity = 0.45;
		if (highest >= minSimilarity) {
			return ImostLikelyToBe;
		} else {
			return null;
		}
	}

	public static AudioTrack getTheMostSuitableItemFromAHashMap(HashMap<AudioTrack, Double> map,
			BlockingQueue<AudioTrack> queue) {
		AudioTrack ImostLikelyToBe = null;
		double highest = 0;

		for (AudioTrack I : queue) {
			if (map.get(I) > highest) {
				highest = map.get(I);
				ImostLikelyToBe = I;
			}
		}
		double minSimilarity = 0.1;
		if (highest >= minSimilarity) {
			return ImostLikelyToBe;
		} else {
			return null;
		}
	}

	/**
	 * used for song search opt
	 * 
	 * @param map
	 * @return words with most hits (a song)
	 * @author thekrystof701
	 */
	public static String getTheMostSuitableStringFromAHashMap(HashMap<String, Integer> map) {
		int top = 0;
		String chosen = null;
		for (HashMap.Entry<String, Integer> entry : map.entrySet()) {

			if (entry.getValue() > top) {
				chosen = entry.getKey();
			}

		}
		return chosen;
	}

	public static String getStringFromArrayOfStrings_withSpaces(ArrayList<String> array) {
		String ret_val = "";
		for (String string : array) {
			ret_val += string + " ";
		}
		return ret_val;
	}

	public static void addReactionToTheMsg(MessageReceivedEvent event, String emoteUNICODE) {
		try {
			event.getMessage().addReaction(emoteUNICODE).queue();
		} catch (Exception e) {
			/* emote may not exist */
		}

	}

	public static String getCurrentDate(boolean includeTime) {
		String format = "dd-MM-yyyy";
		if (includeTime)
			format += " HH_mm_ss";
		System.out.println(format);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(new Date(System.currentTimeMillis()));
	}

	public static void ADD_Text2File(String path, String str) throws IOException, NullPointerException {
		if (path == null)
			path = "H:\\lst_of_existing_sound_files.txt";

		BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
		writer.append("\n");
		writer.append(str);

		writer.close();
	}

	public static String runPyScript(String fullPath, String arguments) {
		try {
			String s;
			Process p = Runtime.getRuntime().exec("py " + fullPath + " " + arguments);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			new BufferedReader(new InputStreamReader(p.getErrorStream()));

			String output = "";
			while (!((s = stdInput.readLine()) == null)) {
				output = s;

			}
			System.out.println("out " + output);
			return output;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static boolean areTwoArrayValuesEqual(ArrayList<?> a, ArrayList<?> b) {
		for (Object objectB : b) {
			for (Object objectA : a) {
				if (objectB == objectA || objectB.equals(objectA))
					return true;
			}
		}

		return false;
	}

	public static boolean isLink(String link) {
		String urlRegex = "((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)";
		Pattern pattern = Pattern.compile(urlRegex);
		Matcher matcher = pattern.matcher(link);
		return matcher.find();

	}
}

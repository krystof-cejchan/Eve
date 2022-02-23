package LIBRARYclass;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.Nullable;

import ListeningCommands.IListeningCommands;
import ListeningCommands.ListeningCommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * 
 * This Class serves as a Library Class according to the design patterns in Java
 * All methods in this class are defined as static, so that they can be called
 * without creating an instance of this class
 */
public class LibraryClass {
	public static boolean compareTwoArrays(ArrayList<String> listA, ArrayList<String> listB) {
		boolean found = false;

		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if (listA.get(i).equalsIgnoreCase(listB.get(j))) {
					found = true;
				}

			}

		}

		return found;
	}

	@Nullable
	public static int whereAreTwoArraysTheSame(ArrayList<String> listA, ArrayList<String> listB) {
		int where = 0;

		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if (listA.get(i).equalsIgnoreCase(listB.get(j))) {
					where = j;
				}

			}

		}
		// returns index from the SECOND arraylist
		return where;

	}

	public static Color getRandomColor() {
		return (new Color(generateRandomInt(0, 255), generateRandomInt(0, 255), generateRandomInt(0, 255)));
	}

	public static int generateRandomInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max);

	}

	public static double averageOfDoubleArray(ArrayList<Double> arrInput) {
		double temp = 0;
		for (int i = 0; i < arrInput.size(); i++) {
			temp += arrInput.get(i);
		}
		return temp / arrInput.size();
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

	public static void addReactionToTheMsg(MessageReceivedEvent event, String emoteUNICODE) {
		try {
			event.getMessage().addReaction(emoteUNICODE).queue();
		} catch (Exception e) {
			/* emote may not exist */
		}

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
			return output;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}

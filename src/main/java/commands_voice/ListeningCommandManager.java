package commands_voice;

import java.util.ArrayList;
import java.util.HashMap;
import javax.annotation.Nullable;

import _library_class.LibraryClass;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;
import voice_and_listening.SpeechToText;

public class ListeningCommandManager {
	public final static ArrayList<IListeningCommands> commands = new ArrayList<>();

	public ListeningCommandManager() {
		addNewCommand(new _ChangeDefaultLanguage());
		addNewCommand(new _Hello());
		addNewCommand(new _PlaySong());
	}

	private void addNewCommand(IListeningCommands Icmd) {

		boolean exists = commands.stream().anyMatch((matched) -> matched.getName().equals(Icmd.getName()));

		if (!exists) {
			commands.add(Icmd);
		}

	}

	public static ArrayList<IListeningCommands> getAllCommands() {
		return commands;
	}

	/**
	 * what we aim to do here is rather simple but quite difficult to create. We
	 * need to find the most suitable command from the user's voice input. Meaning
	 * that we have to compare every word that the user said with the description
	 * and tags of every command (description = return value of @whatDoIdo()) and
	 * then we calculate an average of an average values from description and tags
	 * most likely, we will state some certain value that will serve as a minimum
	 * value of the calculated average (if average is too low, it may mean that the
	 * input was incorrect) then we compare all calculated averages and the most
	 * reliable average will be chosen and its command will be executed.
	 * 
	 * long story short: GET THE MOST SUITABLE COMMAND FROM USER'S VOICE INPUT
	 */
	@Nullable
	public IListeningCommands getCommand(String usersVoiceInput) {

		try {

			/*
			 * byte[] bytes = usersVoiceInput.getBytes(StandardCharsets.UTF_8);
			 * 
			 * String a = new String(bytes, StandardCharsets.UTF_8); System.out.println(a);
			 */
			if ((SpeechToText.Language.getLang().equals("en-GB")
					|| SpeechToText.Language.getLang().equals("en-US")) == false) {
				usersVoiceInput = LibraryClass.runPyScript(
						"C:\\Users\\kryst\\git\\repository3\\discordbottest\\src\\main\\java\\External_Files\\translator.py",
						usersVoiceInput);

			}

			if (LibraryClass.isUserInputVerySimilarToTags(usersVoiceInput) != null)
				return LibraryClass.isUserInputVerySimilarToTags(usersVoiceInput);

			HashMap<IListeningCommands, Double> suitabilityMap = new HashMap<>();
			/*
			 * hashmap contains commands and their average value of suitability
			 * eg.ChangeLanguageCommand has suitability 0.29
			 */
			ArrayList<Double> tempResults = new ArrayList<>();

			SimilarityStrategy strategy = new JaroWinklerStrategy();
			// String targetAsDescr;
			StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
			System.out.println(commands.size());
			for (IListeningCommands theInterfaceExample : commands) {
				/*
				 * targetAsDescr = theInterfaceExample.whatDoIDo();
				 * tempResults.add(service.score(targetAsDescr, usersVoiceInput));
				 */
				double tempResult_FORLOOP = 0;
				for (int i = 0; i < theInterfaceExample.getTags().size(); i++) {
					tempResult_FORLOOP += service.score(theInterfaceExample.getTags().get(i), usersVoiceInput);
				}
				tempResults.add(tempResult_FORLOOP / theInterfaceExample.getTags().size());
				suitabilityMap.put(theInterfaceExample, LibraryClass.averageOfDoubleArray(tempResults));
				System.out
						.println(theInterfaceExample.getName() + " " + LibraryClass.averageOfDoubleArray(tempResults));

			}

			System.out.println(LibraryClass.getTheMostSuitableItemFromAHashMap(suitabilityMap));
			return LibraryClass.getTheMostSuitableItemFromAHashMap(suitabilityMap);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}

}

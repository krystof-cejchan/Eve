package ListeningCommands;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.tools.javac.code.Attribute.Array;

import Speech_Texts_Listening.LANGUAGES;
import Speech_Texts_Listening.SpeechToText;

public class _ChangeDefaultLanguage implements IListeningCommands {

	@Override
	public void doTask() {

		String text = SpeechToText.getText();
		if (text.isEmpty() == false) {

			String[] words = text.split("\\s");
			ArrayList<String> wordsArray = new ArrayList<>();
			for (String word : words) {
				wordsArray.add(word);
			}
			ArrayList<String> languagesArray = new ArrayList<>(LANGUAGES.getAllEnums());

			// compare two arrays

		}

		SpeechToText.setLang("cs-CZ");

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Change Language";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command sets a new default language the bot will proccess all tasks in.";
	}

	@Override
	public ArrayList<String> getTags() {
		ArrayList<String> tags = new ArrayList<>(Arrays.asList

		("language", "set language", "change language", "default language"));

		return tags;
	}

}

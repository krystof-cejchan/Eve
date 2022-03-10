package commands_voice;

import java.util.ArrayList;
import java.util.Arrays;

import _library_class.LibraryClass;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import voice_and_listening.LANGUAGES;
import voice_and_listening.SpeechToText;

public class _ChangeDefaultLanguage implements IListeningCommands {

	@Override
	public void doTask(MessageReceivedEvent event) {

		try {
			String text = SpeechToText.getText();
			if (text.isEmpty() == false) {

				String[] words = text.split("\\s");
				ArrayList<String> wordsArray = new ArrayList<>();
				for (String word : words) {
					wordsArray.add(word);
				}
				ArrayList<String> languagesArray = new ArrayList<>(LANGUAGES.getAllEnums());

				if (LibraryClass.compareTwoArrays(wordsArray, languagesArray)) {

					/*
					 * setting language according to the user's input audio, if found it has to be
					 * rewritten to "shorter" version eg. english→ en-GB
					 */

					SpeechToText.Language.setLang(LANGUAGES.getShortLang(LANGUAGES.valueOf(languagesArray
							.get(LibraryClass.whereAreTwoArraysTheSame(wordsArray, languagesArray)).toLowerCase())));
					event.getChannel()
							.sendMessage("The default language was set to *" + SpeechToText.Language.getLang() + "*")
							.queue();
				}

			}
		} catch (Exception e) {

			event.getChannel().sendMessage("There's been an error \ninfo:" + e).queue();

		}

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
		return new ArrayList<String>(Arrays.asList("language", "set language", "change language", "default language"));
	}

}

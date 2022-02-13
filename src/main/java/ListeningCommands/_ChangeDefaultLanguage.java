package ListeningCommands;

import java.util.ArrayList;
import java.util.Arrays;

import Main.CurrentTextChannel;
import Main.LibraryClass;
import Speech_Texts_Listening.LANGUAGES;
import Speech_Texts_Listening.SpeechToText;
import net.dv8tion.jda.api.entities.Guild;

public class _ChangeDefaultLanguage implements IListeningCommands {
	Guild guild;

	@Override
	public void doTask() {
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

					SpeechToText.setLang(LANGUAGES.getShortLang(LANGUAGES.valueOf(languagesArray
							.get(LibraryClass.whereAreTwoArraysTheSame(wordsArray, languagesArray)).toLowerCase())));
					guild.getTextChannelById(CurrentTextChannel.getId())
							.sendMessage("The default language was set to *" + SpeechToText.getLang() + "*").queue();
				}

			}
		} catch (Exception e) {

			guild.getTextChannelById(CurrentTextChannel.getId())
					.sendMessage("there's been a problem when executing a task. " + e).queue();

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
		ArrayList<String> tags = new ArrayList<>(Arrays.asList

		("language", "set language", "change language", "default language"));

		return tags;
	}

}

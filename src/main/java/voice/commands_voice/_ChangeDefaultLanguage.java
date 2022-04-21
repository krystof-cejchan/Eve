package voice.commands_voice;

import _library_class.LibraryClass;
import enums.LANGUAGES.LANGUAGES;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import voice.voice_and_listening.SpeechToText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _ChangeDefaultLanguage implements IListeningCommands {

    @Override
    public void doTask(MessageReceivedEvent event, String usersInput) throws NullPointerException {

        try {

            if (usersInput != null) {

                String[] words = usersInput.split("\\s");
                ArrayList<String> wordsArray = new ArrayList<>();
                Collections.addAll(wordsArray, words);
                ArrayList<String> languagesArray = new ArrayList<>(LANGUAGES.getAllEnums());

                if (LibraryClass.compareTwoArrays(wordsArray, languagesArray)) {
                    /*
                     * setting language according to the user's input audio, if found it has to be
                     * rewritten to "shorter" version eg. english→ en-GB
                     */
                    try {
                        SpeechToText.Language.setLang(LANGUAGES.getShortLang(LANGUAGES.valueOf(languagesArray
                                .get(LibraryClass.whereAreTwoArraysTheSame(wordsArray, languagesArray)).toLowerCase())));
                        event.getChannel().sendMessage("The default language was set to **"
                                + LANGUAGES.getProperLanFromShort(SpeechToText.Language.getLang()) + " "
                                + LANGUAGES.getLangFlag(
                                LANGUAGES.getProperLanFromShort(SpeechToText.Language.getLang()))
                                + "**").queue();
                    } catch (IllegalArgumentException e) {
                        event.getChannel()
                                .sendMessage(
                                        "The default language was set to *" + SpeechToText.Language.getLang() + "*")
                                .queue();
                    }
                }

            }
        } catch (Exception e) {

            event.getChannel().sendMessage("There's been an error \ninfo:" + e).queue();

        }

    }

    @Override
    public String getName() {
        return "Change Language";
    }

    @Override
    public String whatDoIDo() {
        return "This command sets a new default language the bot will proccess all tasks in.";
    }

    @Override
    public Boolean isParamRequired() {
        return true;
    }

    @Override
    public ArrayList<String> getTags() {
        return new ArrayList<>(Arrays.asList("language", "set language", "change language", "default language"));
    }

}

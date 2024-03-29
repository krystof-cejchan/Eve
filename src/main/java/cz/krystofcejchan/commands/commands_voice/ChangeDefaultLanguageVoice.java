package cz.krystofcejchan.commands.commands_voice;

import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import cz.krystofcejchan.enums_annotations_exceptions.enums.LANGUAGES.LANGUAGES;
import cz.krystofcejchan.utility_class.UtilityClass;
import cz.krystofcejchan.voice.voice_and_listening.SpeechToText;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ChangeDefaultLanguageVoice implements IListeningCommands {
    /**
     * finds suitable language if possible and sets default language to this suitable language
     *
     * @param event      {@link MessageReceivedEvent} for communication with the user
     * @param usersInput user's audio input transcribed to text
     * @throws NullPointerException no language matches the input words
     */
    @Override
    public void doTask(MessageReceivedEvent event, String usersInput) throws NullPointerException {

        try {
            if (usersInput != null) {
                String[] words = usersInput.split("\\s");
                ArrayList<String> wordsArray = new ArrayList<>();
                Collections.addAll(wordsArray, words);
                ArrayList<String> languagesArray = new ArrayList<>();
                Arrays.stream(LANGUAGES.values()).toList()
                        .forEach(lang -> languagesArray.add(LANGUAGES.getProperLanguage(lang)));

                if (UtilityClass.compareTwoArrays(wordsArray, languagesArray)) {
                    /*
                     * setting language according to the user's input audio, if found it has to be
                     * rewritten to "shorter" version eg. english→ en-GB
                     */
                    try {
                        if (UtilityClass.whereAreTwoArraysTheSame(wordsArray, languagesArray) == null)
                            return;

                        SpeechToText.Language.setLang(LANGUAGES.getShortLang(LANGUAGES.valueOf(languagesArray
                                .get(UtilityClass.whereAreTwoArraysTheSame(wordsArray, languagesArray))
                                .toLowerCase())));
                        event.getChannel().sendMessage("The default language was set to **"
                                + LANGUAGES.getProperLanFromShort(SpeechToText.Language.getLang()) + " "
                                + LANGUAGES.getLangFlag(
                                LANGUAGES.getProperLanFromShort(SpeechToText.Language.getLang()))
                                + "**").queue();

                    } catch (IllegalArgumentException | NullPointerException e) {
                        event.getChannel()
                                .sendMessage(
                                        "The default language was set to *" + SpeechToText.Language.getLang() + "*")
                                .queue();
                    }
                }

            }
        } catch (FriendlyException e) {
            event.getChannel().sendMessage("There's been an error \ninfo " + e).queue();
        }
    }

    @Override
    public String getName() {
        return "Change Language";
    }

    @Override
    public String whatDoIDo() {
        return "This command sets a new default language the bot will process all tasks in.";
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

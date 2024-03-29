package cz.krystofcejchan.commands.commands_voice;

import cz.krystofcejchan.audioplayer.PlayCommand;
import cz.krystofcejchan.enums_annotations_exceptions.enums.LANGUAGES.LANGUAGES;
import cz.krystofcejchan.enums_annotations_exceptions.enums.MessageTypes;
import cz.krystofcejchan.link_to_externalfiles.ExternalFileNamesE;
import cz.krystofcejchan.link_to_externalfiles.InputStreamHolder;
import cz.krystofcejchan.utility_class.UtilityClass;
import cz.krystofcejchan.voice.voice_and_listening.SpeechToText;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author krystof-cejchan
 * @see IListeningCommands
 */

public class PlaySongVoice implements IListeningCommands {

    @Override
    public void doTask(MessageReceivedEvent event, String usersInput) throws Exception {


        assert usersInput != null;
        String[] args = usersInput.split(" ");

        PlayCommand playCommand = new PlayCommand();
        event.getChannel().sendMessage("Searching for " + extracttheSong(args)).queue();
        System.out.println(extracttheSong(args));
        playCommand.playMusic(event, extracttheSong(args), false, MessageTypes.EMBED_MESSAGE, usersInput,
                false, false);

    }

    @Override
    public String getName() {
        return "Play a Track";
    }

    @Override
    public String whatDoIDo() {
        return "This command adds a track to the queue or plays it right away";
    }

    @Override
    public Boolean isParamRequired() {
        return true;
    }

    @Override
    public ArrayList<String> getTags() {
        return new ArrayList<>(Arrays.asList

                ("play", "play song", "play track"));
    }


    /**
     * this function tries to extract a song name from String[] @param args
     *
     * @param args is what users said separated with spaces
     * @return a song if possible
     * @throws IndexOutOfBoundsException if starter reaches forbiddenWords array
     *                                   size
     */
    private String extracttheSong(String[] args) throws IndexOutOfBoundsException {
        // HashMap<String, Integer> hMap = new HashMap<>();
        /* .toLowerCase() */
        ArrayList<String> searchWords = new ArrayList<>(Arrays.asList(args));

        System.out.println(searchWords);
        for (String forbidden : forbiddenWords()) {
            System.out.println(searchWords);
            if (!(SpeechToText.Language.getLang().equals("en-GB") || SpeechToText.Language.getLang().equals("en-US")))
                searchWords.removeIf(currWord -> {
                    assert InputStreamHolder.fileNameToPathMap != null;
                    return UtilityClass.runPyScript(InputStreamHolder.fileNameToPathMap.get(ExternalFileNamesE.TRANSLATOR).toString(),
                            currWord, false).equalsIgnoreCase(forbidden);
                });
            else searchWords.removeIf(currWord -> currWord.equalsIgnoreCase(forbidden));
        }

        return UtilityClass.getStringFromArrayOfStrings_withSpaces(searchWords);

        /*
         * AllArrayCombinations_Algorithm.getCombinations(searchWords).forEach((item) ->
         * { System.out.println(item); String path =
         * "C:\\Users\\kryst\\git\\repository3\\discordbottest\\src\\main\\java\\External_Files\\GetSongSearchResult_s.py";
         * String output = LibraryClass.runPyScript(path, item); hMap.put(item,
         * Integer.valueOf(output)); });
         */

        /*
         * unused due to high slowness for (String comb :
         * AllArrayCombinations_Algorithm.getCombinations(searchWords)) {
         * System.out.println(comb); String path =
         * "C:\\Users\\kryst\\git\\repository3\\discordbottest\\src\\main\\java\\External_Files\\GetSongSearchResult_s.py";
         * String output = LibraryClass.runPyScript(path, comb); hMap.put(comb,
         * Integer.valueOf(output)); }
         *
         * // AllArrayCombinations_Algorithm.getCombinations(searchWords); return
         * LibraryClass.getTheMostSuitableStringFromAHashMap(hMap);
         */
    }

    public static ArrayList<String> forbiddenWords() {
        ArrayList<String> forbidden = new ArrayList<>();
        forbidden.add("play");
        forbidden.add("by");
        // forbidden.add("");
        return forbidden;

    }

    public static class ForbiddenWords {
        public static HashMap<String, ArrayList<String>> getForbiddenWordsTranslated() {
            HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
            ArrayList<String> argList = new ArrayList<>();
            for (LANGUAGES language : LANGUAGES.values()) {
                System.out.println(language);
                for (String forbiddenWord : PlaySongVoice.forbiddenWords()) {
                    assert InputStreamHolder.fileNameToPathMap != null;
                    argList.add(UtilityClass.runPyScript(InputStreamHolder.fileNameToPathMap.get(ExternalFileNamesE.TRANSLATORCUSTOM).toString(),
                            LANGUAGES.getShortLang(language)
                                    .substring(0, LANGUAGES.getShortLang(language).indexOf("-")) + " en " + forbiddenWord, false));
                }
                hashMap.put(LANGUAGES.getShortLang(language), argList);
            }
            return hashMap;
        }
    }

}

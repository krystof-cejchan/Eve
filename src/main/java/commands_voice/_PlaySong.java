package commands_voice;

import java.util.ArrayList;
import java.util.Arrays;

import _library_class.LibraryClass;
import audio_player.MessageTypes;
import audio_player.PlayCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import voice_and_listening.SpeechToText;

/**
 * @author krystof-cejchan
 * @see IListeningCommands
 */

public class _PlaySong implements IListeningCommands {

    @Override
    public void doTask(MessageReceivedEvent event) throws Exception {

        String text = SpeechToText.getText();
        String[] args = text.split(" ");

        PlayCommand playCommand = new PlayCommand();
        event.getChannel().sendMessage("Searching for " + extracttheSong(args)).queue();
        playCommand.playMusic(event, extracttheSong(args), false, MessageTypes.EMBED_MESSAGE, text);

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
    public ArrayList<String> getTags() {
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
     * @throws IndexOutOfBoundsException if starter reaches forbiddenWords array
     *                                   size
     */
    private String extracttheSong(String[] args) throws IndexOutOfBoundsException {
        // HashMap<String, Integer> hMap = new HashMap<>();
        /* .toLowerCase() */
        ArrayList<String> searchWords = new ArrayList<>(Arrays.asList(args));


        for (String forbidden : forbiddenWords()) {

            searchWords.removeIf(currWord -> currWord.equalsIgnoreCase(forbidden));

        }
        System.out.println(searchWords);
        return LibraryClass.getStringFromArrayOfStrings_withSpaces(searchWords);

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

    private ArrayList<String> forbiddenWords() {
        ArrayList<String> forbidden = new ArrayList<>();
        forbidden.add("play");
        forbidden.add("by");
        // forbidden.add("");
        return forbidden;

    }

}

package utility_class;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import commands.commands_voice.IListeningCommands;
import commands.commands_voice.ListeningCommandManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;
import voice.PythonASCIIDecoding;

import javax.annotation.Nullable;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * This Class serves as a Library Class according to the design patterns in Java
 * All methods in this class are defined as static, so that they can be called
 * without creating an instance of this class
 *
 * @author krystof-cejchan
 */
public class UtilityClass {
    private UtilityClass() {
        throw new UnsupportedOperationException("cannot create an instance of a utility class");
    }

    /**
     * if two values match ignoring case, true will be returned
     *
     * @param listA ArrayList1
     * @param listB ArrayList2
     * @return boolean true or false
     */
    public static boolean compareTwoArrays(ArrayList<String> listA, ArrayList<String> listB) {
        for (String s : listA) {
            if (listB.contains(s)) return true;
        }

        return false;
    }

    /**
     * @param listA list of strings
     * @param listB list of strings
     * @return true, if a word from all the string in an array matches another word from all the strings from another array
     * @throws PatternSyntaxException if cannot split(" ")
     */
    public static boolean compareItemsInTwoArrays(ArrayList<String> listA, ArrayList<String> listB) throws PatternSyntaxException {

        for (String s : listA) {
            for (String value : listB) {
                for (int i = 0; i < s.split(" ").length; i++) {
                    for (int j = 0; j < value.split(" ").length; j++) {
                        if (s.split(" ")[i].equalsIgnoreCase(value.split(" ")[j])) return true;
                    }
                }
            }
        }

        return false;
    }

    public static Integer[] whereAreItemsInTwoArraysTheSame(ArrayList<String> listA, ArrayList<String> listB)
            throws PatternSyntaxException {
        ArrayList<Integer> where = new ArrayList<>();
        for (String s : listA) {
            for (String value : listB) {
                for (int i = 0; i < s.split(" ").length; i++) {
                    for (int j = 0; j < value.split(" ").length; j++) {
                        if (s.split(" ")[i].equalsIgnoreCase(value.split(" ")[j])) where.add(listB.indexOf(value));
                    }
                }
            }
        }

        return where.toArray(new Integer[0]);
    }

    /**
     * @param listA ArrayList1
     * @param listB ArrayList2
     * @return index as Integer from listB where listA matches listB
     */
    @Nullable
    public static Integer whereAreTwoArraysTheSame(ArrayList<String> listA, ArrayList<String> listB) {
        for (String s : listA) {
            for (int j = 0; j < listB.size(); j++) {
                if (s.equalsIgnoreCase(listB.get(j))) {
                    return j;  // returns index from the SECOND arraylist
                }

            }

        }

        return null;

    }

    /**
     * Generates a random RGB {@link Color}
     *
     * @return Color with randomly chosen RGB (0-255)
     */
    public static Color getRandomColor() {
        return (new Color(generateRandomInt(0, 255), generateRandomInt(0, 255), generateRandomInt(0, 255)));
    }

    /**
     * Generates random int value between and including min and max
     *
     * @param min minimal value
     * @param max maximal value
     * @return Random int value
     */
    public static int generateRandomInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;

    }

    /**
     * Calculates average value from Double ArrayList values
     *
     * @param arrInput ArrayList<Double>
     * @return average value calculated from all ArrayList values
     */
    public static double averageOfDoubleArray(ArrayList<Double> arrInput) {
        double temp = 0;
        for (Double aDouble : arrInput) {
            temp += aDouble;
        }
        return temp / arrInput.size();
    }

    /**
     * Divides input to single words and then checks if the words match any of the tags {@link IListeningCommands}
     *
     * @param input meant as user's voice input transcribed to text and passed here
     * @return IListeningCommands if possible, else null
     */
    @Nullable
    public static IListeningCommands isUserInputVerySimilarToTags(String input) {
        if (input == null || input.isEmpty() || input.isBlank()) return null;

        ArrayList<String> words = new ArrayList<>(Arrays.asList(input.toLowerCase().split(" ")));
        ArrayList<Double> tempResults = new ArrayList<>();
        HashMap<IListeningCommands, Double> map = new HashMap<>();
        SimilarityStrategy strategy = new JaroWinklerStrategy();
        StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
        for (IListeningCommands IListeningCmd : new ListeningCommandManager().getAllCommands()) {
            try {
                double tempResult_FORLOOP = 0;
                if (compareTwoArrays(IListeningCmd.getTags(), words)) {
                    for (int i = 0; i < IListeningCmd.getTags().size(); i++) {
                        tempResult_FORLOOP += service.score(IListeningCmd.getTags().get(i), input);
                    }
                    tempResults.add(tempResult_FORLOOP / IListeningCmd.getTags().size());
                    map.put(IListeningCmd, averageOfDoubleArray(tempResults));
                }

            } catch (Exception e) {
                return null;
            }
        }

        if (getTheMostSuitableIListeningCommandFromAHashMap(map) != null)
            return getTheMostSuitableIListeningCommandFromAHashMap(map);
        else return null;
    }

    /**
     * returns the most suitable {@link IListeningCommands} if its tag similarity is higher than 0.45
     *
     * @param map {@link ListeningCommandManager}
     * @return IListeningCommands if possible, otherwise null
     */
    @Nullable
    public static IListeningCommands getTheMostSuitableIListeningCommandFromAHashMap(HashMap<IListeningCommands, Double> map) {
        IListeningCommands ImostLikelyToBe = null;
        double highest = 0;

        for (IListeningCommands I : new ListeningCommandManager().getAllCommands()) {
            if (map.containsKey(I)) {
                if (map.get(I) > highest) {
                    highest = map.get(I);
                    ImostLikelyToBe = I;
                }
            }
        }
        //double minSimilarity = 0.45;
        if (highest >= .45/*minSimilarity*/) {
            return ImostLikelyToBe;
        } else {
            return null;
        }
    }

    /**
     * @param map   of AudioTrack and Double
     * @param queue of audiotracks
     * @return {@link AudioTrack} which is the most suitable from the map
     */
    public static AudioTrack getTheMostSuitableAudioTrackFromAHashMap(HashMap<AudioTrack, Double> map,
                                                                      BlockingQueue<AudioTrack> queue,
                                                                      double minimalSimilarity) {
        AudioTrack ImostLikelyToBe = null;
        double highest = 0;

        for (AudioTrack I : queue) {
            if (map.get(I) > highest) {
                highest = map.get(I);
                ImostLikelyToBe = I;
            }
        }


        if (highest >= minimalSimilarity) {
            return ImostLikelyToBe;
        } else {
            return null;
        }
    }

    /**
     * transforms a String array to continuous text with spaces
     *
     * @param array ArrayList of String
     * @return String from ArrayList + " " + next String...
     */
    public static String getStringFromArrayOfStrings_withSpaces(ArrayList<String> array) {
        StringBuilder ret_val = new StringBuilder();
        array.forEach(item -> ret_val.append(item).append(" "));
        return ret_val.toString();
    }

    /**
     * add a reaction to the message from {@link MessageReceivedEvent}
     *
     * @param event        MessageReceivedEvent
     * @param emoteUNICODE unicode of an emote <a href="https://unicode.org/emoji/charts/full-emoji-list.html#:~:text=1-,U%2B1F600,-%F0%9F%98%80"> link to emoji list </a>
     */
    public static void addReactionToTheMsg(MessageReceivedEvent event, String emoteUNICODE)
            throws UnsupportedOperationException, net.dv8tion.jda.api.exceptions.InsufficientPermissionException,
            IllegalArgumentException, IllegalStateException {

        event.getMessage().addReaction(emoteUNICODE).queue();

    }

    /**
     * gets currentDate dd-MM-yyyy ([HH_mm_ss]-optional depending on param)
     *
     * @param includeTime whether time should be added to the date or not
     * @return Date in form of String
     */

    public static String getCurrentDate(boolean includeTime) {
        String format = "dd-MM-yyyy";
        if (includeTime) format += " HH_mm_ss";
        System.out.println(format);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date(System.currentTimeMillis()));
    }

    /**
     * runs python script and returns text value that the script returned
     *
     * @param fullPath      to script
     * @param arguments     that will be passed to the script
     * @param consoleOutput true->you'll receive script output in the console
     * @return value from script
     */
    public static String runPyScript(String fullPath, @Nullable String arguments, boolean consoleOutput) {
        try {
            String s;
            Process p = Runtime.getRuntime().exec("python " + fullPath + " " + arguments);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            new BufferedReader(new InputStreamReader(p.getErrorStream()));

            StringBuilder output = new StringBuilder();

            while (!((s = stdInput.readLine()) == null))
                output.append(s).append("\n");

            if (consoleOutput)
                System.out.println(PythonASCIIDecoding.decodeASCIItext("out " + output));
            return PythonASCIIDecoding.decodeASCIItext(output.deleteCharAt(output.length() - 1).toString());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * checks whether param is link or not
     *
     * @param link web URL
     * @return true if link is truly link, else false
     */
    public static boolean isLink(String link) {
        String urlRegex = "((http://|https://)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(/([a-zA-Z-_/.0-9#:?=&;,]*)?)?)";
        return Pattern.compile(urlRegex).matcher(link).find();

    }

    /**
     * gets and returns text from webpage
     *
     * @param web_url webpage url
     * @return text from webpage
     */
    public static String getTextFromWebpage(String web_url) {
        try {
            if (!isLink(web_url)) return null;
            Scanner sc = new Scanner(new URL(web_url).openStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext())
                sb.append(sc.next());

            sc.close();
            return sb.toString().replaceAll("<[^>]*>", "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param milliseconds milliseconds
     * @return meaningful time value according to format<br> {@code .format("%02d:%02d:%02d", hours, minutes, seconds)}
     * or {@code .format("%02d:%02d", minutes, seconds)}
     */
    public static String getTimeStampMilliToStringTime(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
        int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds)
                : String.format("%02d:%02d", minutes, seconds);

        // if (hours > 0) return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        // else return String.format("%02d:%02d", minutes, seconds);
    }
}

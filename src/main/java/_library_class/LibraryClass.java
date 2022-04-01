package _library_class;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import voice.commands_voice.IListeningCommands;
import voice.commands_voice.ListeningCommandManager;

import javax.annotation.Nullable;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This Class serves as a Library Class according to the design patterns in Java
 * All methods in this class are defined as static, so that they can be called
 * without creating an instance of this class
 */
public class LibraryClass {
    /**
     * if two values match ignoring case, true will be returned
     *
     * @param listA ArrayList1
     * @param listB ArrayList2
     * @return boolean true or false
     */
    public static boolean compareTwoArrays(ArrayList<String> listA, ArrayList<String> listB) {
        for (String s : listA) {
            for (String value : listB) {
                if (s.equalsIgnoreCase(value)) {
                    return true;
                }

            }

        }

        return false;
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
        for (IListeningCommands I : ListeningCommandManager.getAllCommands()) {
            try {
                ArrayList<String> words = new ArrayList<>();
                Collections.addAll(words, input.toLowerCase().split(" "));
                if (compareTwoArrays(I.getTags(), words)) {
                    return I;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /**
     * returns the most suitable {@link IListeningCommands} if its tag similarity is higher than 0.45
     *
     * @param map {@link ListeningCommandManager}
     * @return IListeningCommands if possible, otherwise null
     */
    @Nullable
    public static IListeningCommands getTheMostSuitableItemFromAHashMap(HashMap<IListeningCommands, Double> map) {
        IListeningCommands ImostLikelyToBe = null;
        double highest = 0;

        for (IListeningCommands I : ListeningCommandManager.getAllCommands()) {
            if (map.get(I) > highest) {
                highest = map.get(I);
                ImostLikelyToBe = I;
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
    public static AudioTrack getTheMostSuitableItemFromAHashMap(HashMap<AudioTrack, Double> map, BlockingQueue<AudioTrack> queue) {
        AudioTrack ImostLikelyToBe = null;
        double highest = 0;

        for (AudioTrack I : queue) {
            if (map.get(I) > highest) {
                highest = map.get(I);
                ImostLikelyToBe = I;
            }
        }
        double minSimilarity = 0.1;
        if (highest >= minSimilarity) {
            return ImostLikelyToBe;
        } else {
            return null;
        }
    }

    /**
     * used for song search optimalization
     *
     * @param map of String and Integer
     * @return words with most hits (a song)
     * @author krystof-cejchan
     */
    @SuppressWarnings("unused")
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

    /**
     * transforms a String array to continuous text with spaces
     *
     * @param array ArrayList of String
     * @return String from ArrayList + " " + next String...
     */
    public static String getStringFromArrayOfStrings_withSpaces(ArrayList<String> array) {
        StringBuilder ret_val = new StringBuilder();
        for (String string : array) {
            ret_val.append(string).append(" ");
        }
        return ret_val.toString();
    }

    /**
     * add a reaction to the message from {@link MessageReceivedEvent}
     *
     * @param event        MessageReceivedEvent
     * @param emoteUNICODE unicode of an emote <a href="https://unicode.org/emoji/charts/full-emoji-list.html#:~:text=1-,U%2B1F600,-%F0%9F%98%80"> link to emoji list </a>
     */
    public static void addReactionToTheMsg(MessageReceivedEvent event, String emoteUNICODE) throws UnsupportedOperationException,
            net.dv8tion.jda.api.exceptions.InsufficientPermissionException, IllegalArgumentException, IllegalStateException {

        event.getMessage().addReaction(emoteUNICODE).queue();


    }

    /**
     * gets currentDate dd-MM-yyyy ([HH_mm_ss]-optional depending on param)
     *
     * @param includeTime whether time should be added to the date or not
     * @return Date in form of String
     */
    @SuppressWarnings("unused")
    public static String getCurrentDate(boolean includeTime) {
        String format = "dd-MM-yyyy";
        if (includeTime) format += " HH_mm_ss";
        System.out.println(format);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date(System.currentTimeMillis()));
    }

    @SuppressWarnings("unused")
    public static void ADD_Text2File(String path, String str) throws IOException, NullPointerException {
        if (path == null) path = "H:\\lst_of_existing_sound_files.txt";

        BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
        writer.append("\n");
        writer.append(str);

        writer.close();
    }

    /**
     * runs python script and returns text value that the script returned
     *
     * @param fullPath  to script
     * @param arguments that will be passed to the script
     * @return value from script
     */
    public static String runPyScript(String fullPath, @Nullable String arguments) {
        try {
            String s;
            Process p = Runtime.getRuntime().exec("py " + fullPath + " " + arguments);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

            new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String output = "";
            while (!((s = stdInput.readLine()) == null)) {
                output = s;

            }
            System.out.println("out " + output);
            return output;
        } catch (IOException e) {

            e.printStackTrace();
            return "";
        }
    }

    @SuppressWarnings("unused")
    public static boolean areTwoArrayValuesEqual(ArrayList<?> a, ArrayList<?> b) {
        for (Object objectB : b) {
            for (Object objectA : a) {
                if (objectB == objectA || objectB.equals(objectA)) return true;
            }
        }

        return false;
    }

    /**
     * checks whether param is link or not
     *
     * @param link web URL
     * @return true if link is truly link, else false
     */
    public static boolean isLink(String link) {
        String urlRegex = "((http://|https://)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(/([a-zA-Z-_/.0-9#:?=&;,]*)?)?)";
        Pattern pattern = Pattern.compile(urlRegex);
        Matcher matcher = pattern.matcher(link);
        return matcher.find();

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
            URL url = new URL(web_url);
            Scanner sc = new Scanner(url.openStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            sc.close();
            return sb.toString().replaceAll("<[^>]*>", "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

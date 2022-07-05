package commands.commands_voice;

import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;
import utility_class.UtilityClass;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;

import static utility_class.UtilityClass.isUserInputVerySimilarToTags;


public class ListeningCommandManager {
    public final static ArrayList<IListeningCommands> commands = new ArrayList<>();

    public ListeningCommandManager() {
        addNewCommand(new ChangeDefaultLanguageVoice());
        addNewCommand(new HelloVoice());
        addNewCommand(new PlaySongVoice());
        addNewCommand(new SkipToNextSongVoice());
    }

    public static ArrayList<IListeningCommands> getAllCommands() {
        return commands;
    }

    private void addNewCommand(IListeningCommands Icmd) {

        boolean exists = commands.stream().anyMatch(matched -> matched.getName().equals(Icmd.getName()));

        if (!exists) {
            commands.add(Icmd);
        }

    }

    /**
     * what we aim to do here is rather simple but quite difficult to create. We
     * need to find the most suitable command from the user's voice input. Meaning
     * that we have to compare every word that the user said with the description
     * and tags of every command (description = return value of @whatDoIdo()) of
     * every command implementing IListeningCommands interface and then we calculate
     * an average of an average values from description and tags most likely, we
     * will state some certain value that will serve as a minimum value of the
     * calculated average (if average is too low, it may mean that the input was
     * incorrect) then we compare all calculated averages and the most reliable
     * average will be chosen and its command will be executed. long story short:
     * GET THE MOST SUITABLE COMMAND FROM USER'S VOICE INPUT
     *
     * @param usersVoiceInput → user's voice input transcribed to text
     *                        {@link String}
     * @return the most suitable class implementing the interface
     * @author krystof-cejchan <br>
     * <i>02 / 2022</i>
     */
    @Nullable
    public IListeningCommands getCommand(String usersVoiceInput) {

        try {
            if (isUserInputVerySimilarToTags(usersVoiceInput) != null)
                return isUserInputVerySimilarToTags(usersVoiceInput);

            HashMap<IListeningCommands, Double> suitabilityMap = new HashMap<>();
            /*
             * hashmap contains commands and their average value of suitability
             * eg.ChangeLanguageCommand has suitability 0.29
             */
            ArrayList<Double> tempResults = new ArrayList<>();

            SimilarityStrategy strategy = new JaroWinklerStrategy();

            StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
            System.out.println(commands.size());
            //if(AreTwoArrayValuesEqual_Algo.areTwoArrayValuesEqual(arrayList, arrayList2))

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
                suitabilityMap.put(theInterfaceExample, UtilityClass.averageOfDoubleArray(tempResults));
                System.out
                        .println(theInterfaceExample.getName() + " " + UtilityClass.averageOfDoubleArray(tempResults));

            }

            System.out.println(UtilityClass.getTheMostSuitableIListeningCommandFromAHashMap(suitabilityMap));
            return UtilityClass.getTheMostSuitableIListeningCommandFromAHashMap(suitabilityMap);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}

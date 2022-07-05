package objects;

import static utility_class.UtilityClass.getTextFromWebpage;

public class ScriptPathPointer {

    public static String translator = getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/translator_pyscript.html");

    public static String soundFile2Text = getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/soundFile2Text.html");

    public static String loopedVoiceListening = getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/loopedVoiceListening.html");

    public static String songResult = getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/songResult.html");

    public static String dbPath = getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/dbPath.html");

    public static String customTranslator = getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/customTranslator.html");

}

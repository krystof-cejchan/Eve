package objects;

import _library_class.LibraryClass;

@SuppressWarnings("unused")
public class ScriptPathPointer{

    public static String translator = LibraryClass
            .getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/translator_pyscript.html");

    public static String soundFile2Text = LibraryClass
            .getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/soundFile2Text.html");

    public static String loopedVoiceListening = LibraryClass
            .getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/loopedVoiceListening.html");

    public static String songResult = LibraryClass
            .getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/songResult.html");

    public static String dbPath = LibraryClass.getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/dbPath.html");

}

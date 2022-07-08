package cz.krystofcejchan.enums_annotations_exceptions.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum QuoteLanguage {
    EN, ES, PT, IT, DE, FR, CS, SK;

    public static QuoteLanguage getLanguageFromString(String str) {
        return switch (str) {
            case "Spanish" -> ES;
            case "Portuguese" -> PT;
            case "Italian" -> IT;
            case "German" -> DE;
            case "French" -> FR;
            case "Czech" -> CS;
            case "Slovak" -> SK;
            default -> EN;
        };
    }

    public static String getStringFromLanguage(QuoteLanguage lan) {
        return switch (lan) {
            case ES -> "Spanish";
            case PT -> "Portuguese";
            case IT -> "Italian";
            case DE -> "German";
            case FR -> "French";
            case CS -> "Czech";
            case SK -> "Slovak";
            default -> "English";
        };
    }

    public static ArrayList<String> getAllProperLanguages() {
        ArrayList<String> list = new ArrayList<>();
        Arrays.stream(QuoteLanguage.values()).toList().forEach(qLan ->
                list.add(getStringFromLanguage(qLan))
        );
        return list;
    }
}

package enums.LANGUAGES;

import java.util.ArrayList;
import java.util.List;

/**
 * Language enum represents all languages users can use to communicate with the bot
 * <a href="https://github.com/krystof-cejchan/Eve/blob/776a6cf9ce57f1f1317ff54deeb64814e9202266/src/main/java/enums/LANGUAGES/LANGUAGES-table.md"> Table of all languages </a>
 */
public enum LANGUAGES {
    arabic, czech, danish, german, greek, englisha/* Australia */, englishuk/* The UK */, englishus/* The USA */,
    spanish, finnish, french, hebrew, hindi, hungarian, indonesian, italian, japanese, korean, dutch, norwegian, polish,
    portuguese, romanian, russian, slovak, swedish, thai, turkish, chinese/* regular */, chinesehk/* honk kong */,
    chineset/* taiwan */;

    /**
     * @param LANG Language
     * @return BCP 47 as text
     */
    public static String getShortLang(LANGUAGES LANG) {
        return switch (LANG) {
            case arabic -> "ar-SA";
            case czech -> "cs-CZ";
            case danish -> "da-DK";
            case german -> "de-DE";
            case greek -> "el-GR";
            case englisha -> "en-AU";
            case englishuk -> "en-GB";
            case englishus -> "en-US";
            case spanish -> "es-ES";
            case finnish -> "fi-FI";
            case french -> "fr-FR";
            case hebrew -> "he-IL";
            case hindi -> "fi-FI";
            case hungarian -> "hu-HU";
            case indonesian -> "id-ID";
            case italian -> "it-IT";
            case japanese -> "ja-JP";
            case korean -> "ko-KR";
            case dutch -> "nl-NL";
            case norwegian -> "no-NO";
            case polish -> "pl-PL";
            case portuguese -> "pt-PT";
            case romanian -> "ro-RO";
            case russian -> "ru-RU";
            case slovak -> "sk-SK";
            case swedish -> "sv-SE";
            case thai -> "th-TH";
            case turkish -> "tr-TR";
            case chinese -> "zh-CN";
            case chinesehk -> "zn-HK";
            case chineset -> "zn-TW";
        };
    }

    /**
     * @param shortLan BCP 47 language tag as a String
     * @return LANGUAGES as Enum
     */
    public static LANGUAGES getProperLanFromShort(String shortLan) {

        return switch (shortLan) {
            case "ar-SA" -> arabic;
            case "cs-CZ" -> czech;
            case "da-DK" -> danish;
            case "de-DE" -> german;
            case "el-GR" -> greek;
            case "en-AU" -> englisha;
            case "en-GB" -> englishuk;
            case "en-US" -> englishus;
            case "es-ES" -> spanish;
            case "fi-FI" -> finnish;
            case "fr-FR" -> french;
            case "he-IL" -> hebrew;
            case "hi-IN" -> hindi;
            case "hu-HU" -> hungarian;
            case "id-ID" -> indonesian;
            case "it-IT" -> italian;
            case "ja-JP" -> japanese;
            case "ko-KR" -> korean;
            case "nl-NL" -> dutch;
            case "no-NO" -> norwegian;
            case "pl-PL" -> polish;
            case "pt-PT" -> portuguese;
            case "ro-RO" -> romanian;
            case "ru-RU" -> russian;
            case "sk-SK" -> slovak;
            case "sv-SE" -> swedish;
            case "th-TH" -> thai;
            case "tr-TR" -> turkish;
            case "zh-CN" -> chinese;
            case "zn-HK" -> chinesehk;
            case "zn-TW" -> chineset;
            default -> englishuk;
        };

    }

    /**
     * @param LANG {@link LANGUAGES}
     * @return text of proper language
     */
    public static String getProperLanguage(LANGUAGES LANG) {
        return switch (LANG) {
            case arabic -> "Arabic";
            case czech -> "Czech";
            case danish -> "Danish";
            case german -> "German";
            case greek -> "Greek";
            case englisha -> "English (Australia)";
            case englishuk -> "English (the UK)";
            case englishus -> "English (the USA)";
            case spanish -> "Spanish";
            case finnish -> "Finnish";
            case french -> "French";
            case hebrew -> "Hebrew";
            case hindi -> "Hindi";
            case hungarian -> "Hungarian";
            case indonesian -> "Indonesian";
            case italian -> "Italian";
            case japanese -> "Japanese";
            case korean -> "Korean";
            case dutch -> "Dutch";
            case norwegian -> "Norwegian";
            case polish -> "Polish";
            case portuguese -> "Portuguese";
            case romanian -> "Romanian";
            case russian -> "Russian";
            case slovak -> "Slovak";
            case swedish -> "Swedish";
            case thai -> "Thai";
            case turkish -> "Turkish";
            case chinese -> "Chinese";
            case chinesehk -> "Chinese (Hong Kong)";
            case chineset -> "Chinese (Taiwan)";
        };
    }

    /**
     * @param LANG {@link LANGUAGES}
     * @return Language flag emoji as String
     */
    public static String getLangFlag(LANGUAGES LANG) {
        return switch (LANG) {
            case arabic -> "🇸🇦";
            case czech -> "🇨🇿";
            case danish -> "🇩🇰";
            case german -> "🇩🇪";
            case greek -> "🇬🇷";
            case englisha -> "🇦🇺";
            case englishuk -> "🇬🇧";
            case englishus -> "🇺🇸";
            case spanish -> "🇪🇸";
            case finnish -> "🇫🇮";
            case french -> "🇫🇷";
            case hebrew -> "🇮🇱";
            case hindi -> "🇮🇳";
            case hungarian -> "🇭🇺";
            case indonesian -> "🇮🇩";
            case italian -> "🇮🇹";
            case japanese -> "🇯🇵";
            case korean -> "🇰🇷";
            case dutch -> "🇳🇱";
            case norwegian -> "🇳🇴";
            case polish -> "🇵🇱";
            case portuguese -> "🇵🇹";
            case romanian -> "🇷🇴";
            case russian -> "🇷🇺";
            case slovak -> "🇸🇰";
            case swedish -> "🇸🇪";
            case thai -> "🇹🇭";
            case turkish -> "🇹🇷";
            case chinese -> "🇨🇳";
            case chinesehk -> "🇭🇰";
            case chineset -> "🇹🇼";
            default -> "🌎";
        };
    }

    /**
     * @return all {@link LANGUAGES} as String saved {@link List}
     */
    public static List<String> getAllEnums() {
        List<String> enums = new ArrayList<>();
        for (LANGUAGES language : LANGUAGES.values()) {
            enums.add(language.toString());
        }
        return enums;
    }

}


package voice_and_listening;

import java.util.ArrayList;
import java.util.List;

public enum LANGUAGES {
	arabic, czech, danish, german, greek, englisha/* Australia */, englishuk/* The UK */, englishus/* The USA */,
	spanish, finnish, french, hebrew, hindi, hungarian, indonesian, italian, japanese, korean, dutch, norwegian, polish,
	portuguese, romanian, russian, slovak, swedish, thai, turkish, chinese/* regular */, chinesehk/* honk kong */,
	chineset/* taiwan */;

	public static String getShortLang(LANGUAGES LANG) {
		String l;
		switch (LANG) {
		case arabic:
			l = "ar-SA";
			break;
		case czech:
			l = "cs-CZ";
			break;
		case danish:
			l = "da-DK";
			break;
		case german:
			l = "de-DE";
			break;
		case greek:
			l = "el-GR";
			break;
		case englisha:
			l = "en-AU";
			break;
		case englishuk:
			l = "en-GB";
			break;
		case englishus:
			l = "en-US";
			break;
		case spanish:
			l = "es-ES";
			break;
		case finnish:
			l = "fi-FI";
			break;
		case french:
			l = "fr-FR";
			break;
		case hebrew:
			l = "he-IL";
			break;
		case hindi:
			l = "fi-FI";
			break;
		case hungarian:
			l = "hu-HU";
			break;
		case indonesian:
			l = "id-ID";
			break;
		case italian:
			l = "it-IT";
			break;
		case japanese:
			l = "ja-JP";
			break;
		case korean:
			l = "ko-KR";
			break;
		case dutch:
			l = "nl-NL";
			break;
		case norwegian:
			l = "no-NO";
			break;
		case polish:
			l = "pl-PL";
			break;
		case portuguese:
			l = "pt-PT";
			break;
		case romanian:
			l = "ro-RO";
			break;
		case russian:
			l = "ru-RU";
			break;
		case slovak:
			l = "sk-SK";
			break;
		case swedish:
			l = "sv-SE";
			break;
		case thai:
			l = "th-TH";
			break;
		case turkish:
			l = "tr-TR";
			break;
		case chinese:
			l = "zh-CN";
			break;
		case chinesehk:
			l = "zn-HK";
			break;
		case chineset:
			l = "zn-TW";
			break;

		default:
			l = "en-GB";
			break;
		}
		return l;
	}

	public static LANGUAGES getProperLanFromShort(String shortLan) {

		switch (shortLan) {
		case "ar-SA":
			return arabic;

		case "cs-CZ":
			return czech;

		case "da-DK":
			return danish;

		case "de-DE":
			return german;

		case "el-GR":
			return greek;

		case "en-AU":
			return englisha;

		case "en-GB":
			return englishuk;

		case "en-US":
			return englishus;

		case "es-ES":
			return spanish;

		case "fi-FI":
			return finnish;

		case "fr-FR":
			return french;

		case "he-IL":
			return hebrew;

		case "hi-IN":
			return hindi;

		case "hu-HU":
			return hungarian;

		case "id-ID":
			return indonesian;

		case "it-IT":
			return italian;

		case "ja-JP":
			return japanese;

		case "ko-KR":
			return korean;

		case "nl-NL":
			return dutch;

		case "no-NO":
			return norwegian;

		case "pl-PL":
			return polish;

		case "pt-PT":
			return portuguese;

		case "ro-RO":
			return romanian;

		case "ru-RU":
			return russian;

		case "sk-SK":
			return slovak;

		case "sv-SE":
			return swedish;

		case "th-TH":
			return thai;

		case "tr-TR":
			return turkish;

		case "zh-CN":
			return chinese;

		case "zn-HK":
			return chinesehk;

		case "zn-TW":
			return chineset;

		default:
			return englishuk;

		}

	}

	public static String getProperLanguage(LANGUAGES LANG) {
		String l;
		switch (LANG) {
		case arabic:
			l = "Arabic";
			break;
		case czech:
			l = "Czech";
			break;
		case danish:
			l = "Danish";
			break;
		case german:
			l = "German";
			break;
		case greek:
			l = "Greek";
			break;
		case englisha:
			l = "English (Australia)";
			break;
		case englishuk:
			l = "English (the UK)";
			break;
		case englishus:
			l = "English (the USA)";
			break;
		case spanish:
			l = "Spanish";
			break;
		case finnish:
			l = "Finnish";
			break;
		case french:
			l = "French";
			break;
		case hebrew:
			l = "Hebrew";
			break;
		case hindi:
			l = "Hindi";
			break;
		case hungarian:
			l = "Hungarian";
			break;
		case indonesian:
			l = "Indonesian";
			break;
		case italian:
			l = "Italian";
			break;
		case japanese:
			l = "Japanese";
			break;
		case korean:
			l = "Korean";
			break;
		case dutch:
			l = "Dutch";
			break;
		case norwegian:
			l = "Norwegian";
			break;
		case polish:
			l = "Polish";
			break;
		case portuguese:
			l = "Portuguese";
			break;
		case romanian:
			l = "Romanian";
			break;
		case russian:
			l = "Russian";
			break;
		case slovak:
			l = "Slovak";
			break;
		case swedish:
			l = "Swedish";
			break;
		case thai:
			l = "Thai";
			break;
		case turkish:
			l = "Turkish";
			break;
		case chinese:
			l = "Chinese";
			break;
		case chinesehk:
			l = "Chinese (Hong Kong)";
			break;
		case chineset:
			l = "Chinese (Taiwan)";
			break;

		default:
			l = "";
			break;
		}
		return l;
	}

	public static String getLangFlag(LANGUAGES LANG) {
		String l;
		switch (LANG) {
		case arabic:
			l = "🇸🇦";
			break;
		case czech:
			l = "🇨🇿";
			break;
		case danish:
			l = "🇩🇰";
			break;
		case german:
			l = "🇩🇪";
			break;
		case greek:
			l = "🇬🇷";
			break;
		case englisha:
			l = "🇦🇺";
			break;
		case englishuk:
			l = "🇬🇧";
			break;
		case englishus:
			l = "🇺🇸";
			break;
		case spanish:
			l = "🇪🇸";
			break;
		case finnish:
			l = "🇫🇮";
			break;
		case french:
			l = "🇫🇷";
			break;
		case hebrew:
			l = "🇮🇱";
			break;
		case hindi:
			l = "🇮🇳";
			break;
		case hungarian:
			l = "🇭🇺";
			break;
		case indonesian:
			l = "🇮🇩";
			break;
		case italian:
			l = "🇮🇹";
			break;
		case japanese:
			l = "🇯🇵";
			break;
		case korean:
			l = "🇰🇷";
			break;
		case dutch:
			l = "🇳🇱";
			break;
		case norwegian:
			l = "🇳🇴";
			break;
		case polish:
			l = "🇵🇱";
			break;
		case portuguese:
			l = "🇵🇹";
			break;
		case romanian:
			l = "🇷🇴";
			break;
		case russian:
			l = "🇷🇺";
			break;
		case slovak:
			l = "🇸🇰";
			break;
		case swedish:
			l = "🇸🇪";
			break;
		case thai:
			l = "🇹🇭";
			break;
		case turkish:
			l = "🇹🇷";
			break;
		case chinese:
			l = "🇨🇳";
			break;
		case chinesehk:
			l = "🇭🇰";
			break;
		case chineset:
			l = "🇹🇼";
			break;

		default:
			l = "🌎";
			break;
		}
		return l;
	}

	public static List<String> getAllEnums() {
		List<String> enums = new ArrayList<String>();
		for (LANGUAGES language : LANGUAGES.values()) {
			enums.add(language.toString());
		}
		return enums;
	}

}

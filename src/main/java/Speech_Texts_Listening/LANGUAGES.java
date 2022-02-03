package Speech_Texts_Listening;

public enum LANGUAGES {
	arabic, czech, danish, german, greek, englisha/* Australia */, englishuk/* The UK */, englishus/* The USA */,
	spanish, finnish, french, hebrew, hindi, hungarian, indonesian, italian, japanese, korean, dutch, norwegian, polish,
	portuguese, romanian, russian, slovak, swedish, thai, turkish, chinese/* regular */, chinesehk/* honk kong */,
	chineset/* taiwan */;

	public String getShortLang(LANGUAGES LANG) {
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
}

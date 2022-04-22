package enums.LANGUAGES

/**
 * Language enum represents all languages users can use to communicate with the bot
 * [ Table of all languages ](https://github.com/krystof-cejchan/Eve/blob/776a6cf9ce57f1f1317ff54deeb64814e9202266/src/main/java/enums/LANGUAGES/LANGUAGES-table.md)
 */
enum class LANGUAGES {
    arabic, czech, danish, german, greek, englisha /* Australia */, englishuk /* The UK */, englishus /* The USA */, spanish, finnish, french, hebrew, hindi, hungarian, indonesian, italian, japanese, korean, dutch, norwegian, polish, portuguese, romanian, russian, slovak, swedish, thai, turkish, chinese /* regular */, chinesehk /* honk kong */, chineset /* taiwan */;

    companion object {
        /**
         * @param LANG Language
         * @return BCP 47 as text
         */
        @JvmStatic
        fun getShortLang(LANG: LANGUAGES?): String {
            return when (LANG) {
                arabic -> "ar-SA"
                czech -> "cs-CZ"
                danish -> "da-DK"
                german -> "de-DE"
                greek -> "el-GR"
                englisha -> "en-AU"
                englishuk -> "en-GB"
                englishus -> "en-US"
                spanish -> "es-ES"
                finnish -> "fi-FI"
                french -> "fr-FR"
                hebrew -> "he-IL"
                hindi -> "fi-FI"
                hungarian -> "hu-HU"
                indonesian -> "id-ID"
                italian -> "it-IT"
                japanese -> "ja-JP"
                korean -> "ko-KR"
                dutch -> "nl-NL"
                norwegian -> "no-NO"
                polish -> "pl-PL"
                portuguese -> "pt-PT"
                romanian -> "ro-RO"
                russian -> "ru-RU"
                slovak -> "sk-SK"
                swedish -> "sv-SE"
                thai -> "th-TH"
                turkish -> "tr-TR"
                chinese -> "zh-CN"
                chinesehk -> "zn-HK"
                chineset -> "zn-TW"
                else -> "en-GB"
            }
        }

        /**
         * @param shortLan BCP 47 language tag as a String
         * @return LANGUAGES as Enum
         */
        @JvmStatic
        fun getProperLanFromShort(shortLan: String?): LANGUAGES {
            return when (shortLan) {
                "ar-SA" -> arabic
                "cs-CZ" -> czech
                "da-DK" -> danish
                "de-DE" -> german
                "el-GR" -> greek
                "en-AU" -> englisha
                "en-GB" -> englishuk
                "en-US" -> englishus
                "es-ES" -> spanish
                "fi-FI" -> finnish
                "fr-FR" -> french
                "he-IL" -> hebrew
                "hi-IN" -> hindi
                "hu-HU" -> hungarian
                "id-ID" -> indonesian
                "it-IT" -> italian
                "ja-JP" -> japanese
                "ko-KR" -> korean
                "nl-NL" -> dutch
                "no-NO" -> norwegian
                "pl-PL" -> polish
                "pt-PT" -> portuguese
                "ro-RO" -> romanian
                "ru-RU" -> russian
                "sk-SK" -> slovak
                "sv-SE" -> swedish
                "th-TH" -> thai
                "tr-TR" -> turkish
                "zh-CN" -> chinese
                "zn-HK" -> chinesehk
                "zn-TW" -> chineset
                else -> englishuk
            }
        }

        /**
         * @param LANG [LANGUAGES]
         * @return text of proper language
         */
        @JvmStatic
        fun getProperLanguage(LANG: LANGUAGES?): String {
            return when (LANG) {
                arabic -> "Arabic"
                czech -> "Czech"
                danish -> "Danish"
                german -> "German"
                greek -> "Greek"
                englisha -> "English (Australia)"
                englishuk -> "English (the UK)"
                englishus -> "English (the USA)"
                spanish -> "Spanish"
                finnish -> "Finnish"
                french -> "French"
                hebrew -> "Hebrew"
                hindi -> "Hindi"
                hungarian -> "Hungarian"
                indonesian -> "Indonesian"
                italian -> "Italian"
                japanese -> "Japanese"
                korean -> "Korean"
                dutch -> "Dutch"
                norwegian -> "Norwegian"
                polish -> "Polish"
                portuguese -> "Portuguese"
                romanian -> "Romanian"
                russian -> "Russian"
                slovak -> "Slovak"
                swedish -> "Swedish"
                thai -> "Thai"
                turkish -> "Turkish"
                chinese -> "Chinese"
                chinesehk -> "Chinese (Hong Kong)"
                chineset -> "Chinese (Taiwan)"
                else -> ""
            }
        }

        /**
         * @param LANG [LANGUAGES]
         * @return Language flag emoji as String
         */
        @JvmStatic
        fun getLangFlag(LANG: LANGUAGES?): String {
            return when (LANG) {
                arabic -> "🇸🇦"
                czech -> "🇨🇿"
                danish -> "🇩🇰"
                german -> "🇩🇪"
                greek -> "🇬🇷"
                englisha -> "🇦🇺"
                englishuk -> "🇬🇧"
                englishus -> "🇺🇸"
                spanish -> "🇪🇸"
                finnish -> "🇫🇮"
                french -> "🇫🇷"
                hebrew -> "🇮🇱"
                hindi -> "🇮🇳"
                hungarian -> "🇭🇺"
                indonesian -> "🇮🇩"
                italian -> "🇮🇹"
                japanese -> "🇯🇵"
                korean -> "🇰🇷"
                dutch -> "🇳🇱"
                norwegian -> "🇳🇴"
                polish -> "🇵🇱"
                portuguese -> "🇵🇹"
                romanian -> "🇷🇴"
                russian -> "🇷🇺"
                slovak -> "🇸🇰"
                swedish -> "🇸🇪"
                thai -> "🇹🇭"
                turkish -> "🇹🇷"
                chinese -> "🇨🇳"
                chinesehk -> "🇭🇰"
                chineset -> "🇹🇼"
                else -> "🌎"
            }
        }

        /**
         * @return all [LANGUAGES] as String saved [List]
         */
        @JvmStatic
        val allEnums: List<String>
            get() {
                val enums: MutableList<String> = ArrayList()
                for (language in values()) {
                    enums.add(language.toString())
                }
                return enums
            }
    }
}
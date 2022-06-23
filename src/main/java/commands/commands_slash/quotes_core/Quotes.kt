package commands.commands_slash.quotes_core

import library_class.LibraryClass

open class Quotes {
    companion object {
        fun getQuote(lang: String): String {
            val request = okhttp3.Request.Builder()
                .url(
                    "https://quotes15.p.rapidapi.com/quotes/random/?language_code="
                            + enums_annotations_exceptions.enums.QuoteLanguage.getLanguageFromString(lang).toString()
                        .lowercase()
                )
                .get()
                .addHeader(
                    "X-RapidAPI-Key", LibraryClass
                        .getTextFromWebpage("http://eveuwu.g6.cz/get_values&paths/api_quotes.html")
                )
                .addHeader("X-RapidAPI-Host", "quotes15.p.rapidapi.com")
                .build()

            val client = okhttp3.OkHttpClient()
            val response = client.newCall(request).execute()
            var ret = ""
            response.body()?.charStream()?.readText()?.let {
                ret = it
            }
            //returns json as String
            //it is later on parsed to JsonObject
            return ret
        }
    }
}
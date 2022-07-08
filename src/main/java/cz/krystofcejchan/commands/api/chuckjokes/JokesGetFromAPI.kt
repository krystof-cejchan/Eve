package cz.krystofcejchan.commands.api.chuckjokes

import okhttp3.OkHttpClient
import okhttp3.Request

open class JokesGetFromAPI {
    companion object {
        fun getJSONResult(): String {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("X-RapidAPI-Key", "ea77a952f3mshc61b5188857cf19p1b4ec0jsn6c6ce8f24fe4")
                .addHeader("X-RapidAPI-Host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .build()

            val response = client.newCall(request).execute()
            var ret = ""
            response.body()?.charStream()?.readText()?.let {
                ret = it
            }
            return ret
        }
    }
}
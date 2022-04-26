package external_files.memes__php

import _library_class.LibraryClass
import java.io.IOException
import java.net.URL
import java.util.*

object GetFunctionsFromPHP {
    @JvmStatic
    fun getCount(weburl: String? = "http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount"): String? {
        // http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount
        var webUrl = weburl
        if (webUrl == null)
            webUrl = "http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount"
        return try {
            if (!LibraryClass.isLink(webUrl)) webUrl = "http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount"
            val url = URL(webUrl)
            val sc = Scanner(url.openStream())
            val sb = StringBuilder()
            while (sc.hasNext()) {
                sb.append(sc.next())
            }
            val result = sb.toString()
            sc.close()
            result.replace("<[^>]*>".toRegex(), "")
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
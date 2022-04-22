package external_files.memes__php

import _library_class.LibraryClass
import java.io.IOException
import java.net.URL
import java.util.*

object GetFunctionsFrom_PHP {
    @JvmStatic
    fun getCount(Wurl: String?): String? {
        // http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount
        var weburl = Wurl
        return try {
            if (!LibraryClass.isLink(weburl)) weburl = "http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount"
            val url = URL(weburl)
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
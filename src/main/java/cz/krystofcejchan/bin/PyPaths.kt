package cz.krystofcejchan.bin

import java.io.File

object PyPaths {
    const val YTSEARCH = "src/main/java/cz/krystofcejchan/external_files/py_scripts/ytsearch.py"
    const val SONGSEARCHRESULT = "src/main/java/cz/krystofcejchan/external_files/py_scripts/GetSongSearchResult_s.py"
    const val MOSTSUITABLESONG = "src/main/java/cz/krystofcejchan/external_files/py_scripts/GettheMostSuitableSong.py"
    const val SOUNDFILETOTEXT = "src/main/java/cz/krystofcejchan/external_files/py_scripts/soundfiletotext.py"
    const val TRANSLATOR = "src/main/java/cz/krystofcejchan/external_files/py_scripts/translator.py"
    const val TRANSLATORFROMCUSTOMLANGUAGE =
        "src/main/java/cz/krystofcejchan/external_files/py_scripts/translator_fromTo_customlang.py"

    @JvmStatic
    fun absolutePath(path: String): String {
        return File(path).absolutePath
    }
}
package voice

/**
 * @author krystof-cejchan
 * Decoding ASCII encoded text
 */
object PythonASCII_Decoding {
    /**
     * Transforms an ascii text to normal readable text with utf-8 encoding
     * For example <u>[99 104 97 110 103 101 32 116 104 101 32 108 97 110 103 117 97 103 101 32 116 111 32 67 122 101 99 104 32 108 97 110 103 117 97 103 101]</u>
     * **equals to **<u>change the language to Czech language</u>
     *
     * @param asciiEncodedText text encoded in ascii
     * @return decoded text from ascii
     */
    @JvmStatic
    fun decodeASCIItext(asciiEncodedText: String?): String? {
        return try {
            if (asciiEncodedText == null || asciiEncodedText.chars()
                    .anyMatch { codePoint: Int -> Character.isLetter(codePoint) }
            ) return asciiEncodedText


            //String rawString = LibraryClass.runPyScript("C:\\Users\\vecer\\IdeaProjects\\Eve\\src\\main\\java\\external_files\\py_scripts\\soundfiletotext.py", "C:\\Users\\vecer\\Music\\sample.wav cs-CZ");
            val ascii = asciiEncodedText.split(" ".toRegex()).toTypedArray()
            val asciiNumbers = IntArray(ascii.size)
            for (i in ascii.indices) {
                asciiNumbers[i] = ascii[i].toInt()
            }
            val str = StringBuilder()
            for (i in asciiNumbers) {
                str.append(i.toChar())
            }
            str.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
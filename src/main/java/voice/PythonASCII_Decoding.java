package voice;

/**
 * @author krystof-cejchan
 * Decoding ASCII encoded text
 */
public class PythonASCII_Decoding {
    /**
     * Transforms an ascii text to normal readable text with utf-8 encoding
     * For example <u>[99 104 97 110 103 101 32 116 104 101 32 108 97 110 103 117 97 103 101 32 116 111 32 67 122 101 99 104 32 108 97 110 103 117 97 103 101]</u>
     * <strong>equals to </strong><u>change the language to Czech language</u>
     *
     * @param asciiEncodedText text encoded in ascii
     * @return decoded ascii
     */
    public static String decodeASCIItext(String asciiEncodedText) {
        try {

            //String rawString = LibraryClass.runPyScript("C:\\Users\\vecer\\IdeaProjects\\Eve\\src\\main\\java\\external_files\\py_scripts\\soundfiletotext.py", "C:\\Users\\vecer\\Music\\sample.wav cs-CZ");
            String[] ascii = asciiEncodedText.split(" ");
            int[] asciiNumbers = new int[ascii.length];
            for (int i = 0; i < ascii.length; i++) {
                asciiNumbers[i] = Integer.parseInt(ascii[i]);
            }

            StringBuilder str = new StringBuilder();

            for (int i : asciiNumbers) {
                str.append((char) i);
            }
            return str.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

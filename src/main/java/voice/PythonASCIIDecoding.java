package voice;

/**
 * @author krystof-cejchan
 * Decoding ASCII encoded text
 */
public class PythonASCIIDecoding {
    /**
     * Transforms an ascii text to normal readable text with utf-8 encoding
     * For example <u>[99 104 97 110 103 101 32 116 104 101 32 108 97 110 103 117 97 103 101 32 116 111 32 67 122 101 99 104 32 108 97 110 103 117 97 103 101]</u>
     * <strong>equals to </strong><u>change the language to Czech language</u>
     *
     * @param asciiEncodedText text encoded in ascii
     * @return decoded text from ascii
     */
    public static String decodeASCIItext(String asciiEncodedText) {
        try {
            //all python scripts should return output in ascii if needed
            if (asciiEncodedText == null || (asciiEncodedText.chars().anyMatch(Character::isLetter)))
                return asciiEncodedText;

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


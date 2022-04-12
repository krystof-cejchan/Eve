package external_files.py_scripts;

import _library_class.LibraryClass;

public class RunAndCheckPythonScriptEncoding {
    public static String runAndCheck() {
        String rawString = LibraryClass.runPyScript("C:\\Users\\vecer\\IdeaProjects\\Eve\\src\\main\\java\\external_files\\py_scripts\\soundfiletotext.py", "C:\\Users\\vecer\\Music\\sample.wav cs-CZ");

        String[] ascii = rawString.split(" ");
        int[] asciiNumbers = new int[ascii.length];
        for (int i = 0; i < ascii.length; i++) {
            asciiNumbers[i] = Integer.parseInt(ascii[i]);
        }

        StringBuilder str = new StringBuilder();

        for (int i : asciiNumbers) {
            str.append((char) i);
        }
        return str.toString();
    }
}

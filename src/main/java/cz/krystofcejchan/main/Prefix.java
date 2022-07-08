package cz.krystofcejchan.main;

public class Prefix {
    private static String value = ";"; // default prefix

    public static String getValue() {
        return value;
    }

    public static void setValue(String value) {
        if (/* value.contains(" ") == false && */ value.length() >= 1)
            Prefix.value = value;
    }

}

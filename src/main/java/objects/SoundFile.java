package objects;

public class SoundFile {
    private static String title;

    public SoundFile(String title) {

        SoundFile.title = title;

    }

    public static String getTitle() {
        return title;
    }

    static public void setTitle(String title) {
        SoundFile.title = title;
    }

    public static String getWholePath() {
        return "H:\\" + getTitle() + ".wav";
    }

}

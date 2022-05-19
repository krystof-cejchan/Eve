package objects;


public class CurrentTextChannel {
    static String id;

    public CurrentTextChannel(String id) {
        CurrentTextChannel.id = id;
    }

    public static String getId() {
        return id;
    }

    public static void setIid(String id) {
        CurrentTextChannel.id = id;
    }

}

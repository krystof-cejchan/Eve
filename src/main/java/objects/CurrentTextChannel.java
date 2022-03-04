package objects;


public class CurrentTextChannel {
	static String id;

	public CurrentTextChannel(String id) {
		CurrentTextChannel.id = id;
	}

	public void setIid(String id) {
		CurrentTextChannel.id = id;
	}

	public static String getId() {
		return id;
	}

}

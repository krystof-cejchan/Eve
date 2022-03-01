package audio_player;

public enum MessageTypes {

	REG_MESSAGE, EMBED_MESSAGE;

	public MessageTypes[] getAll() {
		return MessageTypes.values();
	}

	public MessageTypes getByName(String name) {
		try {
			return MessageTypes.valueOf(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}

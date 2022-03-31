package audio_player;

public enum MessageTypes {

    REG_MESSAGE, EMBED_MESSAGE;

    public MessageTypes[] getAll() {
        return MessageTypes.values();
    }

}

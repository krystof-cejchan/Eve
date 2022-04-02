package audio_player;

/**
 * Enums for message types <br>
 * Regular message → <i>{@code EG_MESSAGE}</i><br>
 * Embed message → <i>{@code EMBED_MESSAGE}</i>
 *
 * @author krystof-cejchan
 * @since 02/04/22
 */
public enum MessageTypes {

    REG_MESSAGE, EMBED_MESSAGE;

    public MessageTypes[] getAll() {
        return MessageTypes.values();
    }

}

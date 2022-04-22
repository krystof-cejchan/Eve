package enums

/**
 * Enums for message types <br></br>
 * Regular message → *`EG_MESSAGE`*<br></br>
 * Embed message → *`EMBED_MESSAGE`*
 *
 * @author krystof-cejchan
 * @since 02/04/22
 */
enum class MessageTypes {
    REG_MESSAGE, EMBED_MESSAGE;

    val all: Array<MessageTypes>
        get() = values()
}
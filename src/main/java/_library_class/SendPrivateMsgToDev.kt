package _library_class

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.entities.PrivateChannel

/**
 * Contains only discord related stuff
 * This Class serves as a Library Class according to the design patterns in Java
 * All methods in this class are defined as static, so that they can be called
 * without creating an instance of this class
 *
 * @author krystof-cejchan
 */
object SendPrivateMsgToDev {
    /**
     * sends a message to the developer
     *
     * @param event [MessageReceivedEvent]
     */
    @JvmStatic
    fun sendDevMsg(event: MessageReceivedEvent, msg: String?, auth: Boolean) {
        if (auth) {
            if (event.author.isBot) return
        }
        val dev = event.jda.getUserById("348358747825111040")!!
        dev.openPrivateChannel().flatMap { channel: PrivateChannel -> channel.sendMessage(msg!!) }.queue()
    }
}
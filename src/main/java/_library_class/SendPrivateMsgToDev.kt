package _library_class

import net.dv8tion.jda.api.entities.PrivateChannel
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

/**
 * an object containing one method which sends a message to the dev
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
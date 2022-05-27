package commands.commands_others

import _library_class.LibraryClass

/**
 * A class generating gifs sendEmotionalDamage, sendJesus
 */
object Asian {
    /**
     * @return a random link from links
     */
    @JvmStatic
    fun sendEmotionalDamage(): String {
        val links = arrayOf(
            "https://tenor.com/bLzfU.gif", "https://tenor.com/bKsTq.gif", "https://tenor.com/bKtZ4.gif",
            "https://tenor.com/bOgeZ.gif", "https://tenor.com/bOco4.gif", "https://tenor.com/bM4pO.gif"
        )
        return links[LibraryClass.generateRandomInt(0, links.size)]
    }

    /**
     * @return a random link from links
     */
    @JvmStatic
    fun sendJesus(): String {
        val links = arrayOf(
            "https://tenor.com/bDxn5.gif", "https://tenor.com/bGgnd.gif", "https://tenor.com/bNFnu.gif",
            "https://tenor.com/bOqal.gif", "https://tenor.com/bO3s3.gif", "https://tenor.com/bOQ0f.gif"
        )
        return links[LibraryClass.generateRandomInt(0, links.size)]
    }
}
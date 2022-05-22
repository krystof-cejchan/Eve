package commands.commands_slash

import commands._pure_commands.subparts.GetWholePlaylist
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.apache.commons.lang3.StringUtils

/**
 * defined as Listener class in StartUp
 */
class AutocompleteSongs : ListenerAdapter() {
    private val songs = ArrayList<String>()

    /**
     * triggered when user tries to use CommandAutoCompleteInteractionEvent
     *
     */
    override fun onCommandAutoCompleteInteraction(event: CommandAutoCompleteInteractionEvent) {
        if (event.name == "skiptosongbyname" && event.focusedOption.name == "tracktitle") {


            val list: List<String> = ArrayList(GetWholePlaylist.getSongsTitles(event.guild))
            songs.addAll(list)

            val subPartOfSongs = ArrayList<String>()

            songs.let { song ->
                subPartOfSongs.addAll(song.filter {
                    StringUtils.containsAnyIgnoreCase(
                        it,
                        event.focusedOption.value
                    )
                })
            }

            if (subPartOfSongs.isEmpty())
                songs.let { subPartOfSongs.addAll(songs) }

            if (subPartOfSongs.size >= 24) {
                val helper = ArrayList<String>(subPartOfSongs)
                subPartOfSongs.clear()

                subPartOfSongs.addAll(helper.subList(0, 25 - 1))
            }


            event.replyChoiceStrings(subPartOfSongs.distinct().toList()).queue()


        }

    }
}


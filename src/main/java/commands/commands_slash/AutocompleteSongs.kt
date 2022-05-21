package commands.commands_slash

import commands._pure_commands.subparts.GetWholePlaylist
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.apache.commons.lang3.StringUtils

class AutocompleteSongs : ListenerAdapter() {
    private val songs = ArrayList<String>()


    override fun onCommandAutoCompleteInteraction(event: CommandAutoCompleteInteractionEvent) {
        if (event.name == "skiptosongbyname" && event.focusedOption.name == "tracktitle") {

            if (GetWholePlaylist.getPlayList(event.guild).size > 25) {
                val list: List<String> = ArrayList(GetWholePlaylist.getSongsTitles(event.guild).subList(0, 25 - 1))
                songs.addAll(list)
            } else {
                val list: List<String> = ArrayList(GetWholePlaylist.getSongsTitles(event.guild))
                songs.addAll(list)
            }
            songs.let { song ->
                event.replyChoiceStrings(song.filter {
                    //it.contains(event.focusedOption.value, ignoreCase = true)
                    StringUtils.containsAnyIgnoreCase(it, event.focusedOption.value)

                }).queue()

                //event.replyChoiceStrings(songs).queue()


                //event.replyChoiceStrings("a").queue()
            }
        }
    }
}


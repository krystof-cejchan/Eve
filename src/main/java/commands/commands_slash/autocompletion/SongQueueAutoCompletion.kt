package commands.commands_slash.autocompletion

import commands._pure_commands.subparts.GetWholePlaylist
import commands.commands_slash.ISlashCommands
import commands.commands_slash.Skip_toSongbyTitleSLASH
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import org.apache.commons.lang3.StringUtils

class SongQueueAutoCompletion : IAutoCompletion {

    override fun getStringChoices(event: CommandAutoCompleteInteractionEvent): MutableList<String> {
        val songs = ArrayList<String>()
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


        return subPartOfSongs.distinct().toMutableList()
    }


    override fun representativeCommand(): ISlashCommands {
        return Skip_toSongbyTitleSLASH()
    }
}
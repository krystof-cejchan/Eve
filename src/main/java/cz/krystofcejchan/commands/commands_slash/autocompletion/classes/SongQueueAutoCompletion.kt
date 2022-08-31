package cz.krystofcejchan.commands.commands_slash.autocompletion.classes

import cz.krystofcejchan.commands.commands_slash.ISlashCommands
import cz.krystofcejchan.commands.commands_slash.autocompletion.IAutoCompletion
import cz.krystofcejchan.commands.commands_slash.music.Skip_toSongbyTitleSlash
import cz.krystofcejchan.commands.purecommands.subparts.GetWholePlaylist
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
        return Skip_toSongbyTitleSlash()
    }
}
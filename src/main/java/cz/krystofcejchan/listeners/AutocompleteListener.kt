package cz.krystofcejchan.listeners

import cz.krystofcejchan.commands.commands_slash.autocompletion.AutoCompleteManager
import cz.krystofcejchan.commands.commands_slash.autocompletion.IAutoCompletion
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * defined as Listener class in Main
 */
class AutocompleteListener : ListenerAdapter() {


    /**
     * triggered when user tries to use CommandAutoCompleteInteractionEvent
     *
     */
    override fun onCommandAutoCompleteInteraction(event: CommandAutoCompleteInteractionEvent) {
        // if (getCommand(event)?.equals(null) == false)

        getCommand(event)?.getStringChoices(event)?.distinct()
            ?.let {

                if (it.isNotEmpty()) {

                    if (it.contains(""))
                        for (song in it) {
                            if (song.isNullOrEmpty())
                                it.drop(it.indexOf(song))
                        }

                    event.replyChoiceStrings(it.toList()).queue()


                }
            }

    }

    private fun getCommand(event: CommandAutoCompleteInteractionEvent): IAutoCompletion? {

        AutoCompleteManager()::getAutoComplete.invoke().forEach {
            run {

                if ((it.representativeCommand().name == event.name) and (it.representativeCommand().argName?.contains(
                        event.focusedOption.name
                    ) == true)
                ) {

                    return it
                }


            }
        }
        return null
    }
}


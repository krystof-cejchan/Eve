package commands.commands_slash.autocompletion

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * defined as Listener class in StartUp
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

                if ((it.representativeCommand().name == event.name) and it.representativeCommand().argName.equals(
                        event.focusedOption.name
                    )
                ) {
                    return it
                }


            }
        }
        return null
    }
}

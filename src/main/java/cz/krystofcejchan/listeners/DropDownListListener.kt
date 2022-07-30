package cz.krystofcejchan.listeners

import cz.krystofcejchan.dropdown_lists.DropdownListManager
import cz.krystofcejchan.dropdown_lists.IDropdownList
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent
import java.util.function.Consumer

class DropDownListListener : Listener() {
    override fun onSelectMenuInteraction(event: SelectMenuInteractionEvent) {
        DropdownListManager().dropdownLists.forEach(Consumer { it: IDropdownList ->
            if (it.identification.equals(
                    event.selectMenu.id,
                    ignoreCase = true
                )
            ) {
                it.handleEvent(event)
                return@Consumer
            }
        })
    }
}
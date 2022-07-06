package listeners

import DropdownLists.DropdownListManager
import DropdownLists.IDropdownList
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent
import java.util.function.Consumer

class DropDownListListener : Listener() {
    override fun onSelectMenuInteraction(event: SelectMenuInteractionEvent) {
        DropdownListManager().dropdownLists.forEach(Consumer { it: IDropdownList ->
            if (it.identificator.equals(
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
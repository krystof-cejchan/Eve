package cz.krystofcejchan.dropdown_lists;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

public interface IDropdownList {
    /**
     * method that performs whatever is needed when user clicks on MenuSelection ))
     *
     * @param event {@link SelectMenuInteractionEvent}
     */
    void handleEvent(SelectMenuInteractionEvent event);

    String getIdentificator();

    ISlashCommands representativeCommand();

}

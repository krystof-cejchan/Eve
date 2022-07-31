package cz.krystofcejchan.dropdown_lists;

import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;

import javax.annotation.Nonnull;

public interface IDropdownList {
    /**
     * method that performs whatever is needed when user clicks on MenuSelection ))
     *
     * @param event {@link SelectMenuInteractionEvent}
     */
    void handleEvent(SelectMenuInteractionEvent event);

    @Nonnull
    String getIdentification();



}

package cz.krystofcejchan.buttons;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import javax.annotation.Nonnull;

public interface IButtons {
    /**
     * @return unique id of the button
     */
    @Nonnull
    String getButtonIdentifier();

    /**
     * code that is run when the button is clicked
     */
    void handleEvent(ButtonInteractionEvent event);
}

package cz.krystofcejchan.buttons;

import net.dv8tion.jda.api.interactions.components.buttons.Button;

import javax.annotation.Nonnull;

public interface IButtons {
    /**
     * @return unique id of the button
     */
    @Nonnull
    String getId();

    /**
     * @return list of buttons which are to be attached to the message
     */
    @Nonnull
    Button getButton();

    /**
     * code that is run when the button is clicked
     */
    void handleEvent();
}

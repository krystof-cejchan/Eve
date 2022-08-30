package cz.krystofcejchan.listeners;

import cz.krystofcejchan.buttons.ButtonManager;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ButtonClickListener extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        new ButtonManager().getButtonClasses()
                .forEach(it -> {
                    if (Objects.requireNonNull(event.getInteraction()
                                    .getButton()
                                    .getId())
                            .contains(it.getButtonIdentifier()))
                        it.handleEvent(event);

                });
    }
}

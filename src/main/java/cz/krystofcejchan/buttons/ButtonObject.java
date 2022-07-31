package cz.krystofcejchan.buttons;

import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ButtonObject implements IButtons {
    public static List<ButtonObject> listOfCreatedInstancesOfThisClass = new ArrayList<>();

    private final Button button;
    private final Consumer<Object> consumer;
    private final Object arg;

    public ButtonObject(@NotNull Button button, Consumer<Object> consumer, Object arg) {

        this.button = button;
        this.consumer = consumer;
        this.arg = arg;
    }

    @NotNull
    @Override
    public String getId() {
        return Objects.requireNonNull(button.getId());
    }

    @NotNull
    @Override
    public Button getButton() {
        return button;
    }

    @Override
    public void handleEvent() {
        consumer.accept(arg);
    }
}

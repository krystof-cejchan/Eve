package commands.commands_slash.autocompletion;

import commands.commands_slash.ISlashCommands;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IAutoCompletion {

    @NotNull List<String> getStringChoices(@NotNull CommandAutoCompleteInteractionEvent event);

    @NotNull ISlashCommands representativeCommand();

   /* default String getEventName() {
        return representativeCommand().getName();
    }

    default String getEventArgName() {
        return representativeCommand().getArgName();
    }*/

}

package cz.krystofcejchan.commands.commands_slash.autocompletion.classes;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.commands_slash.autocompletion.IAutoCompletion;
import cz.krystofcejchan.commands.commands_slash.quotes_facts_and_jokes.FunFact;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FunFactSafetyAutoCompletion implements IAutoCompletion {
    @NotNull
    @Override
    public List<String> getStringChoices(@NotNull CommandAutoCompleteInteractionEvent event) {
        List<String> defValues = List.of(
                "Filter ON", "Filter OFF", "Could be 'Unsafe'"
        );
        List<String> filteredListOfChoices = defValues.stream().filter(it ->
                org.apache.commons.lang3.StringUtils.containsIgnoreCase(it, event.getFocusedOption().getValue())).toList();

        return filteredListOfChoices.isEmpty() ? defValues : filteredListOfChoices;
    }

    @NotNull
    @Override
    public ISlashCommands representativeCommand() {
        return new FunFact();
    }
}

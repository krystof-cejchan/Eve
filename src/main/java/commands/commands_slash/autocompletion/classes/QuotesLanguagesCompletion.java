package commands.commands_slash.autocompletion.classes;

import commands.commands_slash.ISlashCommands;
import commands.commands_slash.QuoteSlashCommand;
import commands.commands_slash.autocompletion.IAutoCompletion;
import enums_annotations_exceptions.enums.QuoteLanguage;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QuotesLanguagesCompletion implements IAutoCompletion {
    @Override
    public @NotNull List<String> getStringChoices(@NotNull CommandAutoCompleteInteractionEvent event) {
        List<String> filteredListOfChoices = QuoteLanguage.getAllProperLanguages().stream().filter(it ->
                org.apache.commons.lang3.StringUtils.containsIgnoreCase(it, event.getFocusedOption().getValue())).toList();

        return filteredListOfChoices.isEmpty() ? QuoteLanguage.getAllProperLanguages() :
                filteredListOfChoices;
    }

    @Override
    public @NotNull ISlashCommands representativeCommand() {
        return new QuoteSlashCommand();
    }
}

package cz.krystofcejchan.commands.commands_slash.autocompletion.classes;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.commands_slash.autocompletion.IAutoCompletion;
import cz.krystofcejchan.commands.commands_slash.trivia_game.TriviaGame;
import cz.krystofcejchan.enums_annotations_exceptions.enums.trivia_game.Difficulty;
import cz.krystofcejchan.enums_annotations_exceptions.enums.trivia_game.Topic;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TriviaAutoCompletion extends TriviaGame implements IAutoCompletion {
    @NotNull
    @Override
    public List<String> getStringChoices(@NotNull CommandAutoCompleteInteractionEvent event) {
        //difficulty
        if (event.getFocusedOption().getName().equalsIgnoreCase(Objects.requireNonNull(getArgName()).get(0))) {
            List<String> difficultyToString = Arrays.stream(Difficulty.values())
                    .toList()
                    .stream()
                    .map(Enum::toString)
                    .toList();
            List<String> filteredListOfChoices = difficultyToString.stream()
                    .filter(it -> org.apache.commons.lang3.StringUtils.containsIgnoreCase(it,
                            event.getFocusedOption().getValue())).toList();

            return filteredListOfChoices.isEmpty() ? difficultyToString : filteredListOfChoices;
        }
        // topic
        if (event.getFocusedOption().getName().equalsIgnoreCase(Objects.requireNonNull(getArgName()).get(1))) {
            List<String> topicsToString = Arrays.stream(Topic.values())
                    .toList()
                    .stream()
                    .map(Enum::toString)
                    .toList();
            List<String> filteredListOfChoices = topicsToString.stream()
                    .filter(it -> org.apache.commons.lang3.StringUtils.containsIgnoreCase(it,
                            event.getFocusedOption().getValue())).toList();

            return filteredListOfChoices.isEmpty() ? topicsToString : filteredListOfChoices;
        }
        return Collections.emptyList();
    }

    @NotNull
    @Override
    public ISlashCommands representativeCommand() {
        return new TriviaGame();
    }
}

package commands.commands_slash.autocompletion.classes;

import commands.commands_slash.ISlashCommands;
import commands.commands_slash.WeatherForecastFor1DayDetailedEmbed;
import commands.commands_slash.autocompletion.IAutoCompletion;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DayAutoCompletion implements IAutoCompletion {
    @Override
    public @NotNull List<String> getStringChoices(@NotNull CommandAutoCompleteInteractionEvent event) {
        return List.of("Today", "Tomorrow", "The Day after Tomorrow");
    }

    @Override
    public @NotNull ISlashCommands representativeCommand() {
        return new WeatherForecastFor1DayDetailedEmbed();
    }
}

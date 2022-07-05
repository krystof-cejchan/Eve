package commands.commands_slash.autocompletion.classes

import commands.commands_slash.ISlashCommands
import commands.commands_slash.WeatherForecastFor1DayDetailedEmbed
import commands.commands_slash.autocompletion.IAutoCompletion
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent

class DayAutoCompletion : IAutoCompletion {
    override fun getStringChoices(event: CommandAutoCompleteInteractionEvent): List<String> {
        return listOf("Today", "Tomorrow", "The Day after Tomorrow")
    }

    override fun representativeCommand(): ISlashCommands {
        return WeatherForecastFor1DayDetailedEmbed()
    }
}
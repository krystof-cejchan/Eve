package cz.krystofcejchan.commands.commands_slash.autocompletion.classes

import cz.krystofcejchan.commands.commands_slash.ISlashCommands
import cz.krystofcejchan.commands.commands_slash.autocompletion.IAutoCompletion
import cz.krystofcejchan.commands.commands_slash.weather.WeatherForecastForDayAndTime
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent
import java.util.*

class WeatherDaysAndTimesAutoCompletion : WeatherForecastForDayAndTime(), IAutoCompletion {
    override fun getStringChoices(event: CommandAutoCompleteInteractionEvent): List<String> {
        // days
        if (event.focusedOption.name == Objects.requireNonNull(argName)[1]) return Arrays.stream(DAY.values())
            .map { obj: DAY -> obj.toString() }
            .toList()

        // times
        return if (event.focusedOption.name == Objects.requireNonNull(
                argName
            )[2]
        ) Arrays.stream(TIME.values()).map { obj: TIME -> obj.toString() }.toList() else emptyList()
    }

    override fun representativeCommand(): ISlashCommands {
        return WeatherForecastForDayAndTime()
    }
}
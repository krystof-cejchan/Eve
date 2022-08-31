package cz.krystofcejchan.commands.commands_slash.weather;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.DAY;
import cz.krystofcejchan.lite_weather_lib.enums_exception.enums.TIME;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.WeatherForecast;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.forecast.days.hour.ForecastAtHour;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class WeatherForecastForDayAndTime implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        List<String> enteredParams = slashEvent.getOptions()
                .stream()
                .map(OptionMapping::getAsString)
                .toList();
        if (Arrays.stream(DAY.values()).map(Enum::toString).noneMatch(enteredParams::contains)
                || Arrays.stream(TIME.values()).map(Enum::toString).noneMatch(enteredParams::contains)) {
            slashEvent.replyEmbeds(new EmbedBuilder().setTitle("Interaction failed").setColor(Color.RED)
                            .addField("Unknown DAY or TIME", "Use DAY and TIME from the hints only", false)
                            .build())
                    .setEphemeral(true).queue();
            return;
        }

        try {
            WeatherForecast weatherForecast = new WeatherForecast(Objects.requireNonNull(slashEvent
                    .getOption((Objects.requireNonNull(getArgName())).get(0))).getAsString(),
                    DAY.valueOf(Objects.requireNonNull(slashEvent.getOption(getArgName().get(1))).getAsString()),
                    TIME.valueOf(Objects.requireNonNull(slashEvent.getOption(getArgName().get(2))).getAsString()));
            slashEvent.replyEmbeds(generateEmbed(weatherForecast).build()).queue();

        } catch (IOException ioException) {
            slashEvent.reply("* something has gone terribly wrong... *\nPlease, try again later or try different location ")
                    .setEphemeral(true).queue();
        }
    }

    private EmbedBuilder generateEmbed(WeatherForecast weatherForecast) {
        EmbedBuilder embedBuilder = new EmbedBuilder().setTimestamp(ZonedDateTime.now())
                .setTitle("Weather forecast for " + weatherForecast.getLocation() + ", " +
                        Arrays.stream(weatherForecast.getDays()).map(Enum::toString)
                                .collect(Collectors.joining(", ")) + " at " +
                        Arrays.stream(weatherForecast.getTimes()).map(Enum::toString).collect(Collectors.joining(", ")));
        generateFields(weatherForecast).forEach(embedBuilder::addField);
        return embedBuilder.setColor(UtilityClass.getRandomColor());
    }

    private List<MessageEmbed.Field> generateFields(WeatherForecast weatherForecast) {
        List<MessageEmbed.Field> fields = new ArrayList<>();
        for (Map<TIME, ForecastAtHour> timeForecastAtHourMap : weatherForecast.getAllForecastForAllDayAndAllTime().values()) {
            for (ForecastAtHour forecastAtHour : timeForecastAtHourMap.values()) {
                fields.add(new MessageEmbed.Field(forecastAtHour.getDay().toString() + " at " + forecastAtHour.getTime().toString(),
                        ("""
                                __***%s***__
                                Temperature °C: %s
                                Feels-like temp °C %s
                                Chance of...: Rain( *%s* ), Snow( *%s* ), Fog( *%s* )
                                Cloud cover: %s
                                Precipitation MM: %s""")
                                .formatted(forecastAtHour.getWeatherDescription(),
                                        forecastAtHour.getTemperatureC(),
                                        forecastAtHour.getFeelsLikeC(),
                                        forecastAtHour.getChanceOfRain(),
                                        forecastAtHour.getChanceOfSnow(),
                                        forecastAtHour.getChanceOfFog(),
                                        forecastAtHour.getCloudCover(),
                                        forecastAtHour.getPrecipMM()), true));
            }
        }
        return fields;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "get weather forecast for today, tomorrow or the day after tomorrow with specific times";
    }

    @NotNull
    @Override
    public String getName() {
        return "weather-for-day_time";
    }

    @NotNull
    @Override
    public ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                        "town / city / location", true, false),
                new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(1),
                        "DAY", true, true),
                new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(2),
                        "TIME", true, true));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return List.of("location", "day", "time");
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return false;
    }

    @NotNull
    @Override
    public List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.WEATHER);
    }
}

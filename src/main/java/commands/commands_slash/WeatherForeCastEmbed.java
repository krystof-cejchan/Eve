package commands.commands_slash;

import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import enums_annotations_exceptions.exceptions.InvalidWebAddress;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import weather.logic.forecast.JSONGrabber;
import weather.logic.forecast.days.DayInWeatherForeCast;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WeatherForeCastEmbed implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            String arg = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0)))
                    .getAsString();

            slashEvent.replyEmbeds(generateEmbed(new JSONGrabber("https://wttr.in/" + (arg
                    .replaceAll(" ", "%20")
                    + "?format=j1"))
                    .getWeatherForecastsForAllDaysAndTimes()).build()).queue();

        } catch (InvalidWebAddress | IOException e) {
            e.printStackTrace();
        }
    }

    private EmbedBuilder generateEmbed(List<DayInWeatherForeCast> weatherForecastsForAllDaysAndTimes) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(new Color(0, 123, 255));
        embedBuilder.setTitle("Weather Forecast \uD83C\uDF21 for " + weatherForecastsForAllDaysAndTimes.get(0).getLocation());
        embedBuilder.setDescription("a detailed weather forecast for today and the 2 following days");

        for (DayInWeatherForeCast day : weatherForecastsForAllDaysAndTimes) {
            embedBuilder.addField(day.getDay(), "", false);
            embedBuilder.addField(GenerateFieldsForDay.getDesc(day));
            embedBuilder.addField(GenerateFieldsForDay.getTemp(day));
            embedBuilder.addField(GenerateFieldsForDay.getChances(day));
            embedBuilder.addField(GenerateFieldsForDay.getRest(day));
        }
        return embedBuilder;
    }

    private static class GenerateFieldsForDay {
        public static MessageEmbed.Field getTemp(DayInWeatherForeCast dayInWeatherForeCast) {
            return new MessageEmbed.Field("Temperature", cutTime(dayInWeatherForeCast.getTime()) + dayInWeatherForeCast.getC() +
                    "°C\t" + dayInWeatherForeCast.getF() + "°F" +
                    "\nFeels like: " + dayInWeatherForeCast.getFeelsC() + "°C\t" + dayInWeatherForeCast.getFeelsF() + "°F",
                    false);
        }

        public static MessageEmbed.Field getDesc(DayInWeatherForeCast dayInWeatherForeCast) {
            return new MessageEmbed.Field("Description", dayInWeatherForeCast.getWeatherValue(),
                    false);
        }

        public static MessageEmbed.Field getChances(DayInWeatherForeCast dayInWeatherForeCast) {
            return new MessageEmbed.Field("Chances of... at " + cutTime(dayInWeatherForeCast.getTime()),
                    "\uD83C\uDF27Rain: " + dayInWeatherForeCast.getChanceofRain() + "%\n"
                            + "\uD83C\uDF28Snow: " + dayInWeatherForeCast.getChanceofSnow() + "%\n" +
                            "\uD83C\uDF29Thunder: " + dayInWeatherForeCast.getChanceofThunder() + "%\n" +
                            "☀Sunshine: " + dayInWeatherForeCast.getChanceofSunshine() + "%",
                    false);
        }

        public static MessageEmbed.Field getRest(DayInWeatherForeCast dayInWeatherForeCast) {
            return new MessageEmbed.Field("Other at " + cutTime(dayInWeatherForeCast.getTime()),
                    "Visibility: " + dayInWeatherForeCast.getVisibilityKm() + "km\n"
                            + "Visibility Miles: " + dayInWeatherForeCast.getVisibilityMiles() + "miles\n" +
                            "WindSpeed: " + dayInWeatherForeCast.getWindSpeedKmph() + "kmph\n" +
                            "WindSpeed miles per hour" + dayInWeatherForeCast.getWindSpeedMph() + "mph",
                    false);
        }

    }

    private static String cutTime(String def) {
        return def.equalsIgnoreCase("0") ? "00:00" : def.substring(0, def.length() - 2) +
                ":" + def.substring(def.length() - 2) + "⌚  ";
    }

    @Override
    public @NotNull String getDescription() {
        return "get a detailed weather forecast for today and the 2 following days";
    }

    @Override
    public @NotNull String getName() {
        return "weather_forecast";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return Collections.singletonList(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                "Paste your city, town or location", true));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return Collections.singletonList("city-town");
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return false;
    }
}

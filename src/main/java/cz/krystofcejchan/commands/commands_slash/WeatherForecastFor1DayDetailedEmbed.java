package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.enums_annotations_exceptions.exceptions.InvalidWebAddress;
import cz.krystofcejchan.weather.logics.forecast.JSONGrabber;
import cz.krystofcejchan.weather.logics.forecast.days.DayInWeatherForeCast;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WeatherForecastFor1DayDetailedEmbed implements ISlashCommands {
    private static String theDay = "";

    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {

            String arg = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0))).getAsString();
            theDay = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(1))).getAsString();
            slashEvent.replyEmbeds(generateEmbed(new JSONGrabber("https://wttr.in/" +
                    (arg.replaceAll(" ", "%20") + "?format=j1"))
                    .getWeatherForecastsForAllDaysAndTimes(2)).build()).queue();

        } catch (InvalidWebAddress | IOException e) {
            e.printStackTrace();
        }
    }

    private EmbedBuilder generateEmbed(List<DayInWeatherForeCast> weatherForecastsForAllDaysAndTimes) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(new Color(0, 123, 255));
        embedBuilder.setTitle("Weather Forecast \uD83C\uDF21 in " + weatherForecastsForAllDaysAndTimes.get(0).getLocation());
        embedBuilder.setFooter("source: www.wttr.in");
        int s = 0;
        if (theDay.equalsIgnoreCase("tomorrow"))
            s = 4;
        if (theDay.equalsIgnoreCase("the day after tomorrow"))
            s = 8;
        for (int d = s; d < s + 4; d++) {
            if (d == s)
                embedBuilder.setDescription("a detailed weather forecast for " + weatherForecastsForAllDaysAndTimes.get(d)
                        .getDay());
            embedBuilder.addField("Time ⌚", cutTime(weatherForecastsForAllDaysAndTimes.get(d).getTime()), false);
            embedBuilder.addField(GenerateFieldsForDay.getDesc(weatherForecastsForAllDaysAndTimes.get(d)));
            embedBuilder.addField(GenerateFieldsForDay.getTemp(weatherForecastsForAllDaysAndTimes.get(d)));
            embedBuilder.addField(GenerateFieldsForDay.getChances(weatherForecastsForAllDaysAndTimes.get(d)));
            embedBuilder.addField(GenerateFieldsForDay.getSunInfo(weatherForecastsForAllDaysAndTimes.get(d), true));
            embedBuilder.addField(GenerateFieldsForDay.getRest(weatherForecastsForAllDaysAndTimes.get(d)));

        }

        return embedBuilder;
    }

    private static class GenerateFieldsForDay {

        public static MessageEmbed.Field getTemp(DayInWeatherForeCast dayInWeatherForeCast) {
            return new MessageEmbed.Field("Temperature", dayInWeatherForeCast.getC() +
                    "°C\t" + dayInWeatherForeCast.getF() + "°F" + "\nFeels like: "
                    + dayInWeatherForeCast.getFeelsC() + "°C\t" + dayInWeatherForeCast.getFeelsF() + "°F", true);
        }

        public static MessageEmbed.Field getDesc(DayInWeatherForeCast dayInWeatherForeCast) {
            return new MessageEmbed.Field("Description", dayInWeatherForeCast.getWeatherValue(), true);
        }

        public static MessageEmbed.Field getChances(DayInWeatherForeCast dayInWeatherForeCast) {
            return new MessageEmbed.Field("Chance of... ",
                    "\uD83C\uDF27Rain: " + dayInWeatherForeCast.getChanceofRain() + "%\n" + "\uD83C\uDF28Snow: "
                            + dayInWeatherForeCast.getChanceofSnow() + "%\n" + "\uD83C\uDF29Thunder: " + dayInWeatherForeCast
                            .getChanceofThunder() + "%\n" + "☀Sunshine: " + dayInWeatherForeCast.getChanceofSunshine() + "%",
                    true);
        }

        public static MessageEmbed.Field getRest(DayInWeatherForeCast dayInWeatherForeCast) {
            return new MessageEmbed.Field("Other ",
                    "Visibility: " + dayInWeatherForeCast.getVisibilityKm() + "km\n" + "Visibility Miles: "
                            + dayInWeatherForeCast.getVisibilityMiles() + "miles\n" + "WindSpeed: " + dayInWeatherForeCast
                            .getWindSpeedKmph() + "kmph\n" + "WindSpeed: " + dayInWeatherForeCast.getWindSpeedMph() + "mph",
                    true);
        }

        public static MessageEmbed.Field getSunInfo(DayInWeatherForeCast dayInWeatherForeCast, boolean uvOnly) {
            String uvIndexIcon = "";
            String[] icons = {"🟩", "🟨", "🟧", "🟥", "🟪"};
            int uvIndex = Integer.parseInt(dayInWeatherForeCast.getUvIndex());
            if (uvIndex <= 2) uvIndexIcon = icons[0];
            if (uvIndex <= 5) uvIndexIcon = icons[1];
            if (uvIndex <= 7) uvIndexIcon = icons[2];
            if (uvIndex <= 10) uvIndexIcon = icons[3];
            if (uvIndex <= 11) uvIndexIcon = icons[4];
            return
                    uvOnly ?
                            new MessageEmbed.Field("UV index", "UV index " + dayInWeatherForeCast.getUvIndex()
                                    + uvIndexIcon, true)
                            :
                            new MessageEmbed.Field("Sun Info", "Sun rise \uD83C\uDF05" + dayInWeatherForeCast.getSunRise()
                                    + "\tSun set \uD83C\uDF07" + dayInWeatherForeCast.getSunSet() + "\nUV index "
                                    + dayInWeatherForeCast.getUvIndex() + uvIndexIcon
                                    , true);
        }

    }

    private static String cutTime(String def) {
        return def.equalsIgnoreCase("0") ? "00:00" : def.substring(0, def.length() - 2)
                + ":" + def.substring(def.length() - 2);
    }

    @Override
    public @NotNull String getDescription() {
        return "get a detailed weather forecast for a specific day";
    }

    @Override
    public @NotNull String getName() {
        return "weather_forecast_detailed";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                        "Paste your city, town or location", true),
                new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(1),
                        "Choose! a day", true, true)
        );
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return List.of("city-town", "day");
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return false;
    }

    @Override
    public @NotNull List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.WEATHER);
    }
}

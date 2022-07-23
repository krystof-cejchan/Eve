package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.enums_annotations_exceptions.enums.weather.ChancesOf;
import cz.krystofcejchan.enums_annotations_exceptions.enums.weather.TemperatureSystem;
import cz.krystofcejchan.enums_annotations_exceptions.exceptions.InvalidWebAddress;
import cz.krystofcejchan.weather.logics.forecast.JSONGrabber;
import cz.krystofcejchan.weather.logics.forecast.days.DayInWeatherForeCast;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WeatherForecastForAllDaysAverageEmbed implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            String arg = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0))).getAsString();

            slashEvent.replyEmbeds(generateEmbed(new JSONGrabber("https://wttr.in/" +
                    (arg.replaceAll(" ", "%20") + "?format=j1"))
                    .getWeatherForecastsForAllDaysAndTimes(1)).build()).queue();

        } catch (InvalidWebAddress | IOException e) {
            e.printStackTrace();
        }
    }

    private EmbedBuilder generateEmbed(List<DayInWeatherForeCast> weatherForecastsForAllDaysAndTimes) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(new Color(0, 123, 255));
        embedBuilder.setTitle("Weather Forecast \uD83C\uDF21 in " + weatherForecastsForAllDaysAndTimes.get(0).getLocation());
        embedBuilder.setDescription("weather forecast for today and the 2 following days");
        for (int d = 0; d < weatherForecastsForAllDaysAndTimes.size(); d += 8) {
            String dayName = "The day after tomorrow";
            if (d < 16) dayName = "Tomorrow";
            if (d < 8) dayName = "Today";
            embedBuilder.addField(dayName,
                    "Average temperature "
                            + ValuesForCertatinDayLite.Temperature.avgTempforDay(weatherForecastsForAllDaysAndTimes, d,
                            TemperatureSystem.C) + "°C / "
                            + ValuesForCertatinDayLite.Temperature.avgTempforDay(weatherForecastsForAllDaysAndTimes, d,
                            TemperatureSystem.F) + "°F\n"
                            + "Max-Min temperature " + ValuesForCertatinDayLite.Temperature
                            .maxminTemp(weatherForecastsForAllDaysAndTimes, d,
                                    TemperatureSystem.C, true) + ", " + ValuesForCertatinDayLite.Temperature
                            .maxminTemp(weatherForecastsForAllDaysAndTimes, d,
                                    TemperatureSystem.C, false) + "°C / "
                            + ValuesForCertatinDayLite.Temperature.maxminTemp(weatherForecastsForAllDaysAndTimes, d,
                            TemperatureSystem.F, true) + ", " + ValuesForCertatinDayLite.Temperature
                            .maxminTemp(weatherForecastsForAllDaysAndTimes, d,
                                    TemperatureSystem.F, false) + "°F\n"
                            + "Chance of rain \uD83C\uDF27\t" + ValuesForCertatinDayLite.Chances
                            .chanceOf(weatherForecastsForAllDaysAndTimes,
                                    ChancesOf.RAIN, d) + "%\n"
                            + "Chance of snow \uD83C\uDF28\t" + ValuesForCertatinDayLite.Chances
                            .chanceOf(weatherForecastsForAllDaysAndTimes,
                                    ChancesOf.SNOW, d) + "%\n"
                            + "Chance of thunder \uD83C\uDF29\t" + ValuesForCertatinDayLite.Chances
                            .chanceOf(weatherForecastsForAllDaysAndTimes,
                                    ChancesOf.THUNDER, d) + "%\n"
                            + "Chance of sunshine ☀\t" + ValuesForCertatinDayLite.Chances
                            .chanceOf(weatherForecastsForAllDaysAndTimes,
                                    ChancesOf.SUNSHINE, d) + "%\n",
                    false);
        }

        return embedBuilder;
    }

    private static class ValuesForCertatinDayLite {
        public static class Temperature {
            public static double avgTempforDay(List<DayInWeatherForeCast> dayInWeatherForeCasts, int n_day,
                                               TemperatureSystem system) {
                String d = dayInWeatherForeCasts.get(n_day).getDay();
                double avg = 0, counter = 0;
                for (DayInWeatherForeCast day : dayInWeatherForeCasts) {
                    if (day.getDay().equals(d)) {
                        if (system == TemperatureSystem.C) avg += Integer.parseInt(day.getC());
                        else avg += Integer.parseInt(day.getF());
                        counter++;
                    }
                }
                return new BigDecimal(avg / counter).setScale(1, RoundingMode.HALF_UP).doubleValue();

            }

            public static double maxminTemp(List<DayInWeatherForeCast> dayInWeatherForeCasts, int n_day,
                                            TemperatureSystem system,
                                            boolean max) {
                String d = dayInWeatherForeCasts.get(n_day).getDay();
                ArrayList<Double> tempArr = new ArrayList<>();
                dayInWeatherForeCasts.forEach(day -> {
                    if (day.getDay().equals(d)) {
                        if (system == TemperatureSystem.C) tempArr.add(Double.valueOf(day.getC()));
                        else tempArr.add(Double.valueOf(day.getF()));
                    }

                });
                return BigDecimal.valueOf(max ? Collections.max(tempArr) : Collections.min(tempArr))
                        .setScale(1, RoundingMode.HALF_UP).doubleValue();
            }


        }

        public static List<String> weatherValue(List<DayInWeatherForeCast> dayInWeatherForeCasts, int n_day) {
            String d = dayInWeatherForeCasts.get(n_day).getDay();
            List<String> retVals = new ArrayList<>();
            for (DayInWeatherForeCast day : dayInWeatherForeCasts) {
                if (!day.getDay().equals(d)) continue;
                if (retVals.contains(day.getWeatherValue())) continue;
                retVals.add(day.getWeatherValue());

            }
            return retVals;
        }

        public static class Chances {
            public static int chanceOf(List<DayInWeatherForeCast> dayInWeatherForeCasts, ChancesOf chancesOf,
                                       int n_day) {
                String d = dayInWeatherForeCasts.get(n_day).getDay();
                int chance = 0, counter = 0;
                for (DayInWeatherForeCast day : dayInWeatherForeCasts) {
                    if (!day.getDay().equals(d)) continue;
                    switch (chancesOf) {
                        case RAIN -> chance += Integer.parseInt(day.getChanceofRain());
                        case SNOW -> chance += Integer.parseInt(day.getChanceofSnow());
                        case THUNDER -> chance += Integer.parseInt(day.getChanceofThunder());
                        case SUNSHINE -> chance += Integer.parseInt(day.getChanceofSunshine());
                    }
                    counter++;

                }
                return chance / counter;

            }
        }

    }

    @Override
    public @NotNull String getDescription() {
        return "get a detailed weather forecast for today and the 2 following days";
    }

    @Override
    public @NotNull String getName() {
        return "weather_forecast_lite";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                "Paste your city, town or location", true)/*,
                new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                        "Choose! a day", true, true)*/
        );
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return List.of("city-town"/*, "day"*/);
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

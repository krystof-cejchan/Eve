package commands.commands_slash;

import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import enums_annotations_exceptions.exceptions.InvalidWebAddress;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import weather.logic.JSONGrabber;
import weather.logic.WeatherCurrent_Obj;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WeatherEmbed implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            //https://wttr.in/a?format=j1
            WeatherCurrent_Obj weather = new JSONGrabber("https://wttr.in/" +
                    Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0))).getAsString()
                    + "?format=j1")
                    .getWeatherForeCast();

            slashEvent.replyEmbeds(generateEmbed(weather).build()).queue();
        } catch (IOException | InvalidWebAddress e) {
            e.printStackTrace();
        }

    }

    private EmbedBuilder generateEmbed(WeatherCurrent_Obj weather) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Current Weather ForeCast in " + weather.getLocation());
        builder.setDescription(weather.getWeather_value());
        builder.addField("Temperature", "C° " + weather.getCurr_tempC() + "\nF° "
                + weather.getCurr_tempF(), true);
        builder.addField("Feels-Like Temperature", "C° " + weather.getCurr_feels_tempC() + "\nF° "
                + weather.getCurr_feels_tempF(), true);
        return builder;
    }

    @Override
    public @NotNull String getDescription() {
        return "Get current weather forecast for today in your location";
    }

    @Override
    public @NotNull String getName() {
        return "current_weather";
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

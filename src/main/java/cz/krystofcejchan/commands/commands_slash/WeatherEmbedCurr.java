package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.buttons.IButtons;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.link_to_externalfiles.ExternalFileNamesE;
import cz.krystofcejchan.link_to_externalfiles.InputStreamHolder;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.current_weather.CurrentCondition;
import cz.krystofcejchan.lite_weather_lib.weather_objects.subparts.nearest_area.NearestArea;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.CheckForNull;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WeatherEmbedCurr implements ISlashCommands, IButtons {
    @CheckForNull
    static private CurrentCondition currentCondition = null;

    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        currentCondition = new CurrentCondition(Objects.requireNonNull(
                slashEvent.getOption(
                        Objects.requireNonNull(
                                getArgName()).get(0))).getAsString());

        slashEvent.replyEmbeds(generateEmbed(currentCondition).build())
                .setEphemeral(false)
                .addActionRow(Button.success(getButtonIdentifier(), "Get full weather report as text"))
                .queue();
    }

    @NotNull
    private EmbedBuilder generateEmbed(@NotNull CurrentCondition currentCondition) {
        NearestArea nearestArea = new NearestArea(currentCondition.getLocation());
        assert InputStreamHolder.fileNameToPathMap != null;
        String pyScriptOutput = UtilityClass.runPyScript(InputStreamHolder.fileNameToPathMap
                        .get(ExternalFileNamesE.LATITUDELONGTITUDE).toString(),
                nearestArea.getAreaInfo().getLatitude() + "," + nearestArea.getAreaInfo().getLongitude(),
                false);
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Current Weather Report in " + pyScriptOutput);
        builder.setColor(new Color(0, 123, 255, 255));
        builder.setDescription(currentCondition.getWeatherDescription());
        builder.addField("Temperature", "C° " + currentCondition.getFeelsLikeC() + "\nF° "
                + currentCondition.getFeelsLikeF(), true);
        builder.addField("Feels-Like Temperature", "C° " + currentCondition.getFeelsLikeC() + "\nF° "
                + currentCondition.getFeelsLikeF(), true);
        builder.addField("Cloud cover", String.valueOf(currentCondition.getCloudCover()), true);
        return builder;
    }

    @Override
    public @NotNull
    String getDescription() {
        return "Get current weather forecast for today in your location";
    }

    @Override
    public @NotNull
    String getName() {
        return "weather_today";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
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

    @Override
    public @NotNull
    List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.WEATHER);
    }

    @NotNull
    @Override
    public String getButtonIdentifier() {
        return "currweather";
    }

    @Override
    public void handleEvent(@NotNull ButtonInteractionEvent event) {
        if (Objects.requireNonNull(event.getButton().getId()).contains("no")) {
            event.deferEdit().queue();
            return;
        }

        if (currentCondition == null) {
            event.getChannel().sendMessage("Your interaction with this button has failed \uD83D\uDE13 \nTry generating " +
                    getName() + " command or try it later").queue();
            return;
        }

        event.editMessageEmbeds(generateEmbed(currentCondition)
                .addField("All as Text", currentCondition.toString(), false)
                .build()).queue();
        event.editButton(Button.secondary(getButtonIdentifier() + "no", " You have already triggered this interaction! ")).queue();
    }
}

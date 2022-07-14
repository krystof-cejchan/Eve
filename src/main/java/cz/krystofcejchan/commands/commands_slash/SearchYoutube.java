package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.external_files.py_scripts.PyPaths;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchYoutube implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        boolean caught = false;
       // slashEvent.deferReply().queue();
        try {
            slashEvent.getOption(getArgName().get(1)).getAsDouble();
        } catch (NullPointerException nullPointerException) {
            caught = true;
        }
        String amountOfResults = caught ? "5 " : java.lang.Math.round(Objects.requireNonNull(slashEvent
                .getOption(Objects.requireNonNull(getArgName()).get(1))).getAsDouble()) + " ";

        String res = UtilityClass.runPyScript(PyPaths.absolutePath(PyPaths.YTSEARCH),
                amountOfResults +
                        Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0))).getAsString(), false);
        slashEvent.replyEmbeds(generateEmbed(res.split("\n")).build()).queue();
    }

    private EmbedBuilder generateEmbed(String[] scriptResults) {
        List<MessageEmbed.Field> fields = new ArrayList<>();
        for (int i = 0; i < scriptResults.length - 1; i += 2) {
            fields.add(new MessageEmbed.Field(scriptResults[i],
                    "[Link to the video](" + scriptResults[i + 1] + ")", true));
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(UtilityClass.getRandomColor());
        embedBuilder.setTitle("Youtube Search Result");
        embedBuilder.setDescription(String.valueOf(scriptResults.length / 2));
        fields.forEach(embedBuilder::addField);
        return embedBuilder;
    }

    @Override
    public @NotNull String getDescription() {
        return "search for youtube videos";
    }

    @Override
    public @NotNull String getName() {
        return "search-yt";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                        "type a text you want to get results for", true, false),
                new OptionData(OptionType.NUMBER, getArgName().get(1), "how many results do you want?", false)
                        .setMinValue(0).setMaxValue(20));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return List.of("search-key", "amount-of-results");
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
        return List.of(SlashCommandCategory.MUSIC, SlashCommandCategory.OTHER);
    }
}

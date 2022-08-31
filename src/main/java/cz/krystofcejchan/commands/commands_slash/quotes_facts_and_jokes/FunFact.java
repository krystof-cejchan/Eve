package cz.krystofcejchan.commands.commands_slash.quotes_facts_and_jokes;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.link_to_externalfiles.ExternalFileNamesE;
import cz.krystofcejchan.link_to_externalfiles.InputStreamHolder;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FunFact implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        boolean argSet = slashEvent.getOptions().size() > 0;
        assert InputStreamHolder.fileNameToPathMap != null;
        String output = UtilityClass.runPyScript(InputStreamHolder.fileNameToPathMap.get(ExternalFileNamesE
                        .RANDOMFACTS).toString(),
                argSet ? optionToPythonArgument(Objects.requireNonNull(
                        slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0))).getAsString()) :
                        "-1", false);

        slashEvent.replyEmbeds(generateEmbed(output)
                .setDescription(argSet ?
                        Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName())
                                .get(0))).getAsString() : "Filter ON").build()).queue();
    }

    @NotNull
    private String optionToPythonArgument(@NotNull String option) {
        if (option.contains("ON")) return "-1";
        if (option.contains("OFF")) return "1";

        return "2";
    }

    private EmbedBuilder generateEmbed(String output) {
        return new EmbedBuilder().setColor(UtilityClass.getRandomColor()).setTitle("Fun-fact!")
                .addField("Your cool fun-fact", "```bash\n\"" + output + "\"\n```", false);
    }

    @NotNull
    @Override
    public String getDescription() {
        return "learn a cool fact";
    }

    @NotNull
    @Override
    public String getName() {
        return "fun-fact";
    }

    @NotNull
    @Override
    public ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return Collections.singletonList(new OptionData(OptionType.STRING,
                Objects.requireNonNull(getArgName()).get(0),
                "safety").setAutoComplete(true));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return Collections.singletonList("safety");
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
        return Collections.singletonList(SlashCommandCategory.ENTERTAINMENT);
    }
}

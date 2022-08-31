package cz.krystofcejchan.commands.commands_slash.quotes_facts_and_jokes;

import cz.krystofcejchan.commands.api.quotes_core.QuoteObject;
import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
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

public class QuoteSlashCommand implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {

        String arg = !(slashEvent.getOptions().size() > 0) ? "English" : Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName())
                .get(0))).getAsString();

        QuoteObject quoteObject = new QuoteObject(arg);
        slashEvent.replyEmbeds(new EmbedBuilder().setColor(UtilityClass.getRandomColor())
                .addField("Your Quote", "```bash\n\"" + quoteObject.getContent() + "\"\nby: " + quoteObject.getAuthor() + "\n```",
                        false)
                .build()).queue();
    }

    @Override
    public @NotNull
    String getDescription() {
        return "get a random quote from a famous person";
    }

    @Override
    public @NotNull
    String getName() {
        return "famous-quote";
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
                "Choose a language from the list", false, true));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return Collections.singletonList("language");
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
        return Collections.singletonList(SlashCommandCategory.MUSIC);
    }
}

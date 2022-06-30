package commands.commands_slash;

import commands.api.quotes_core.QuoteObject;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import library_class.LibraryClass;
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
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {

        boolean caught = false;

        try {
            slashEvent.getOption(getArgName().get(0)).getAsString();
        } catch (NullPointerException nullPointerException) {
            caught = true;
        }
        String arg = caught ? "English" : Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName())
                .get(0))).getAsString();

        QuoteObject quoteObject = new QuoteObject(arg);
        slashEvent.replyEmbeds(new EmbedBuilder().setColor(LibraryClass.getRandomColor())
                .addField("Your Quote", "```bash\n\"" + quoteObject.getContent() + "\"\nby: " + quoteObject.getAuthor() + "\n```",
                        false)
                .build()).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "get a random quote from a famous person";
    }

    @Override
    public @NotNull String getName() {
        return "famousquote";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
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
}

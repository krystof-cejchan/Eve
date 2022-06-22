package commands.commands_slash;

import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import library_class.LibraryClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Help implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(LibraryClass.getRandomColor());
        builder.setTitle("Help command");
        builder.setDescription("All Slash commands (" + new SlashCommandManager().getAllCommands().size() + ")");
        for (ISlashCommands command : new SlashCommandManager().getAllCommands()) {
            StringBuilder optData = new StringBuilder();
            if (command.takesArguments() != ArgumentSlashCommandCount.NONE) {
                optData = new StringBuilder();
                for (OptionData optionData : Objects.requireNonNull(command.getOptionData())) {
                    optData.append(optionData.getName()).append(", ");
                }
                optData = new StringBuilder(Optional.of(optData.toString())
                        .filter(str -> str.lastIndexOf(" ") == str.length() - 1)
                        .map(str -> str.substring(0, str.length() - 2))
                        .orElse(optData.toString()));
            }
            if (command.takesArguments() == ArgumentSlashCommandCount.NONE) {
                builder.addField(command.getName(), "__" + command.getDescription() + "__\nTakes Arguments: **"
                                + command.takesArguments() + "**\nIs guild only: **" + command.isGuildOnly()
                                + "**\nMust user's joined channel match bot's channel: **"
                                + command.isUserRequiredToBeInTheSameChannelAsBot() + "**",
                        true);
            } else {
                builder.addField(command.getName(), "__" + command.getDescription() + "__\nTakes Arguments: **"
                                + command.takesArguments() + "**\t:\t*" + optData + "*\nIs guild only: **"
                                + command.isGuildOnly()
                                + "**\nMust user's joined channel match bot's channel: **"
                                + command.isUserRequiredToBeInTheSameChannelAsBot() + "**",
                        true);
            }

        }
        slashEvent.replyEmbeds(builder.build()).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "returns all slash commands with their description";
    }

    @Override
    public @NotNull String getName() {
        return "help";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.NONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return null;
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return null;
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

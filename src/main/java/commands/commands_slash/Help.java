package commands.commands_slash;

import DropdownLists.HelpDropdownList;
import commands.CommandManager;
import commands.commands_voice.ListeningCommandManager;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Help extends HelpDropdownList implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        MessageBuilder builder = new MessageBuilder();
        int slashCommandSize = new SlashCommandManager().getAllCommands().size(),
                textCommandSize = new CommandManager().getAllCommands().size(),
                voiceCommandSize = new ListeningCommandManager().getAllCommands().size();
        builder.append("Bot currently has:\n");
        builder.append("`").append(String.valueOf(slashCommandSize)).append("` Slash Commands");
        builder.append("\n`").append(String.valueOf(textCommandSize)).append("` Text Commands");
        builder.append("\n`").append(String.valueOf(voiceCommandSize)).append("` Voice Commands");
        builder.append("\n`").append("Total number of commands: ")
                .append(String.valueOf(slashCommandSize + textCommandSize + voiceCommandSize)).append("`");
        builder.append("\n```fix\n");
        builder.append("Choose from a category from Selection Menu down below to see the commands");
        SelectMenu.Builder menu = SelectMenu.create(super.getIdentificator());
        Arrays.stream(SlashCommandCategory.values()).toList().forEach(category ->
                menu.addOption(SlashCommandCategory.getCategoryInFullName(category), category.toString()));
        menu.setPlaceholder("Select commands' category to show up");
        menu.setRequiredRange(1, 1);

        slashEvent.reply(builder.append("\n```").build())
                .addActionRow(menu.build())
                .queue();
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

    @Override
    public @NotNull List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.OTHER);
    }
}

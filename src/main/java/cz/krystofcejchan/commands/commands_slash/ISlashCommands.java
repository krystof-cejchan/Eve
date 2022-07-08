package cz.krystofcejchan.commands.commands_slash;


import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public interface ISlashCommands {
    /**
     * Executes the command
     *
     * @param slashEvent {@link SlashCommandInteractionEvent}
     */
    void executeSlashCommand(SlashCommandInteractionEvent slashEvent);

    /**
     * @return description of the command
     */
    @NotNull
    String getDescription();

    /**
     * @return name of the command
     */
    @NotNull
    String getName();

    @NotNull
    ArgumentSlashCommandCount takesArguments();

    /**
     * options for slash commands
     *
     * @return OptionData
     */
    @Nullable
    List<OptionData> getOptionData();

    @Nullable
    List<String> getArgName();


    boolean isGuildOnly();

    boolean isUserRequiredToBeInTheSameChannelAsBot();

    /**
     * used for /help command to separate commands into categories
     *
     * @return SlashCommandCategory depending on where the command belongs to
     */
    @NotNull
    List<SlashCommandCategory> getCategory();

}

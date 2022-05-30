package commands.commands_slash;


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
    enums_annotations_exceptions.enums.Arguments takesArguments();

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


}

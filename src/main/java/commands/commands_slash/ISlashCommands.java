package commands.commands_slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

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

    boolean takesArguments();

    /**
     * options for slash commands
     *
     * @return OptionData
     */
    @Nullable
    OptionData getOptionData();

    @Nullable
    String getArgName();

    boolean isGuildOnly();


}

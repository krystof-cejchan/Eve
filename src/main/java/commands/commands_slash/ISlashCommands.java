package commands.commands_slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

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
    String getDescription();

    /**
     * @return name of the command
     */
    String getName();

    boolean isGuildOnly();
}

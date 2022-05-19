package commands.commands_slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

public class __test__ implements ISlashCommands {


    /**
     * Executes the command
     *
     * @param slashEvent {@link SlashCommandInteractionEvent}
     */
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        slashEvent.reply("This is just a quick test reply").queue();
    }

    /**
     * @return description of the command
     */
    @Override
    public @NotNull String getDescription() {
        return "testík";
    }

    /**
     * @return name of the command
     */
    @Override
    public @NotNull String getName() {
        return "uwuTEST";
    }

    @Override
    public boolean takesArguments() {
        return false;
    }

    /**
     * options for slash commands
     *
     * @return OptionData
     */
    @Override
    public OptionData getOptionData() {
        return null;
    }

    @Override
    public String getArgName() {
        return null;
    }


    @Override
    public boolean isGuildOnly() {
        return false;
    }
}

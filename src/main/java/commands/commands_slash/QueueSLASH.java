package commands.commands_slash;

import commands._pure_commands.Queue_PURE;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class QueueSLASH implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        slashEvent.reply(Queue_PURE.getQueueAndReturnItAsReadyMessage(slashEvent.getGuild())).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "Shows queue of songs";
    }

    @Override
    public @NotNull String getName() {
        return "queue";
    }

    @Override
    public boolean takesArguments() {
        return false;
    }

    @Nullable
    @Override
    public OptionData getOptionData() {
        return null;
    }

    @Nullable
    @Override
    public String getArgName() {
        return null;
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }
}

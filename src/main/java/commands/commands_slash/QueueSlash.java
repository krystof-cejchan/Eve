package commands.commands_slash;

import commands.purecommands.QueuePure;
import enums_annotations_exceptions.enums.Arguments;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class QueueSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        slashEvent.reply(QueuePure.getQueueAndReturnItAsReadyMessage(slashEvent.getGuild())).queue();
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
    public @NotNull Arguments takesArguments() {
        return Arguments.NONE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return null;
    }

    @Override
    public List<String> getArgName() {
        return null;
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }
}

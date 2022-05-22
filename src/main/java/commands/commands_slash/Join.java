package commands.commands_slash;

import enums_annotations_exceptions.annotations.Slash;
import main.VoiceChannels;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Slash()
public class Join implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        new VoiceChannels().joinSlash(slashEvent, true);
    }

    @Override
    public @NotNull String getDescription() {
        return "Join User's voice channel";
    }

    @Override
    public @NotNull String getName() {
        return "join_me";
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

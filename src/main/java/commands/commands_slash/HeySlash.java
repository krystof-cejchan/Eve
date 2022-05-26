package commands.commands_slash;

import enums_annotations_exceptions.annotations.Slash;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voice.voice_and_listening.SpeechToText;

@Slash()
public class HeySlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            new SpeechToText().onEchoSlashCommand(slashEvent);
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull String getDescription() {
        return "Listening to your voice to trigger a command";
    }

    @Override
    public @NotNull String getName() {
        return "hey";
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

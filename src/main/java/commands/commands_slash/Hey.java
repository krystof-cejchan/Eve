package commands.commands_slash;

import enums_annotations_exceptions.annotations.Slash;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import voice.voice_and_listening.SpeechToText;

import java.util.List;

@Slash()
public class Hey implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            slashEvent.reply("*get ready, set and ...*").queue();
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
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.NONE;
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

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return true;
    }


}

package commands.commands_slash;

import audio_player.PlayCommand;
import enums_and_annotations.annotations.Slash;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Slash()
public class PlaySong implements ISlashCommands {
    /**
     * Executes the command
     *
     * @param slashEvent {@link SlashCommandInteractionEvent}
     */
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        new PlayCommand().playMusicFromSlash(slashEvent,
                Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()))).getAsString(),
                null);

    }

    /**
     * @return description of the command
     */
    @Override
    public @NotNull String getDescription() {
        return "Play a track";
    }

    /**
     * @return name of the command
     */
    @Override
    public @NotNull String getName() {
        return "play_uwu";
    }

    @Override
    public boolean takesArguments() {
        return true;
    }

    /**
     * options for slash commands
     *
     * @return OptionData
     */
    @Nullable
    @Override
    public OptionData getOptionData() {
        return new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()), "paste track url or title", true);
    }

    @Nullable
    @Override
    public String getArgName() {
        return "track";
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }
}

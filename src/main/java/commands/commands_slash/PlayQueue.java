package commands.commands_slash;

import _library_class.LibraryClass;
import audio_player.PlayQCommand;
import enums_and_annotations.annotations.Slash;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@Slash()
public class PlayQueue implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        String arg = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()))).getAsString();
        new PlayQCommand().playMusicFromSlash(slashEvent, arg, LibraryClass.isLink(arg));
    }

    @Override
    public @NotNull String getDescription() {
        return "Plays each song from playlist";
    }

    @Override
    public @NotNull String getName() {
        return "playqueue_uwu";
    }

    @Override
    public boolean takesArguments() {
        return true;
    }

    @Nullable
    @Override
    public OptionData getOptionData() {
        return new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()), "paste tracks' url",
                true);
    }

    @Nullable
    @Override
    public String getArgName() {
        return "tracks";
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }
}

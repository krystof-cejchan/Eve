package commands.commands_slash;

import audioplayer.PlayQCommand;
import enums_annotations_exceptions.annotations.Slash;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import library_class.LibraryClass;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slash()
public class PlayQueueSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        String arg = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(Objects.requireNonNull(getArgName())
                .get(0)))).getAsString();
        new PlayQCommand().playMusicFromSlash(slashEvent, arg, LibraryClass.isLink(arg));
    }

    @Override
    public @NotNull String getDescription() {
        return "Plays each song from playlist";
    }

    @Override
    public @NotNull String getName() {
        return "playqueue";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(Objects
                .requireNonNull(getArgName()).get(0)),
                "paste tracks' url",
                true)));
    }

    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("tracks"));
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

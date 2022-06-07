package commands.commands_slash;

import audioplayer.PlayQCommand;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import library_class.LibraryClass;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayQImmediately implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        String arg = Objects.requireNonNull(slashEvent.getOption
                (Objects.requireNonNull(getArgName()).get(0))).getAsString();
        new PlayQCommand().playMusicFromSlash(slashEvent, arg,
                LibraryClass.isLink(arg), true);
    }

    @Override
    public @NotNull String getDescription() {
        return "Adds a play-list to the queue and plays it right away";
    }

    @Override
    public @NotNull String getName() {
        return "playqueueimmediately";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return Collections.singletonList(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                "song title or url", true));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return Collections.singletonList("playlist");
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

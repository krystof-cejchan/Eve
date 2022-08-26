package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.audioplayer.PlayQCommand;
import cz.krystofcejchan.enums_annotations_exceptions.annotations.Slash;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slash()
public class PlayQueueSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {

        boolean playImmediately, caught = false;

        try {
            slashEvent.getOption
                    (getArgName().get(1)).getAsString();
        } catch (NullPointerException nullPointerException) {
            caught = true;
        }

        playImmediately = !caught && Objects.requireNonNull(slashEvent.getOption(getArgName().get(1))).getAsBoolean();

        String arg = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(Objects.requireNonNull(getArgName())
                .get(0)))).getAsString();

        new PlayQCommand().playMusicFromSlash(slashEvent, arg, UtilityClass.isLink(arg), playImmediately,false);
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
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(Objects
                .requireNonNull(getArgName()).get(0)),
                "paste tracks' url",
                true), new OptionData(OptionType.BOOLEAN, Objects.requireNonNull(Objects.requireNonNull(getArgName())
                .get(1)), "True → list will be moved to the beginning of the queue;",
                false, false)));
    }

    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("tracks", "play-immediately"));
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return true;
    }

    @Override
    public @NotNull List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.MUSIC);
    }
}

package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.audioplayer.PlayCommand;
import cz.krystofcejchan.enums_annotations_exceptions.annotations.Slash;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slash()
public class PlaySongSlash implements ISlashCommands {
    /**
     * Executes the command
     *
     * @param slashEvent {@link SlashCommandInteractionEvent}
     */
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

        new PlayCommand().playMusicFromSlash(slashEvent, Objects.requireNonNull(slashEvent.getOption
                        (Objects.requireNonNull(getArgName()).get(0))).getAsString(),
                null, playImmediately,false);

    }

    /**
     * @return description of the command
     */
    @Override
    public @NotNull String getDescription() {
        return "Plays a track";
    }

    /**
     * @return name of the command
     */
    @Override
    public @NotNull String getName() {
        return "playsong";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    /**
     * options for slash commands
     *
     * @return OptionData
     */
    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(Objects.requireNonNull(getArgName())
                        .get(0)), "paste track url or title",
                        true, false),
                new OptionData(OptionType.BOOLEAN, Objects.requireNonNull(Objects.requireNonNull(getArgName())
                        .get(1)), "True → track will be moved to the beginning of the queue;",
                        false, false)
        ));
    }

    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("source-title_or_link", "play-immediately"));
    }

    @Override
    public boolean isGuildOnly() {
        return false;
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

package cz.krystofcejchan.commands.commands_slash.music;

import cz.krystofcejchan.audioplayer.PlayCommand;
import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.enums_annotations_exceptions.annotations.Slash;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
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

        boolean caught = slashEvent.getOptions().stream().map(OptionMapping::getName)
                .anyMatch(name -> name.equals(Objects.requireNonNull(getArgName()).get(1)));

        new PlayCommand().playMusicFromSlash(slashEvent, Objects.requireNonNull(slashEvent.getOption
                        (Objects.requireNonNull(getArgName()).get(0))).getAsString(), null,
                caught, false);
    }

    /**
     * @return description of the command
     */
    @Override
    public @NotNull
    String getDescription() {
        return "Plays a track";
    }

    /**
     * @return name of the command
     */
    @Override
    public @NotNull
    String getName() {
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
        return List.of("source-title_or_link", "play-immediately");
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
    public @NotNull
    List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.MUSIC);
    }
}

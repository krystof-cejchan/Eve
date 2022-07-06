package bin;

import audioplayer.PlayCommand;
import commands.commands_slash.ISlashCommands;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayImmediately implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {

        new PlayCommand().playMusicFromSlash(slashEvent, Objects.requireNonNull(slashEvent.getOption
                        (Objects.requireNonNull(getArgName()).get(0))).getAsString(),
                null, true,true);

    }


    @Override
    public @NotNull String getDescription() {
        return "Adds a track to the queue and plays it right away";
    }

    @Override
    public @NotNull String getName() {
        return "playimmediately";
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
        return Collections.singletonList("song");
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

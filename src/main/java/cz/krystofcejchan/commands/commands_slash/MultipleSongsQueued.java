package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.audioplayer.PlayCommand;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MultipleSongsQueued implements ISlashCommands {
    private final int SONG_LIMIT_PER_COMMAND = 25;

    /**
     * adds as many songs as a user provided to the queue
     *
     * @param slashEvent {@link SlashCommandInteractionEvent}
     */
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        //  boolean[] caughtArgs = new boolean[slashEvent.getOptions().size()];
        //  Arrays.fill(caughtArgs, false);
        ArrayList<String> argsValues = new ArrayList<>();
        for (OptionMapping options : slashEvent.getOptions()) {
            try {
                argsValues.add(options.getAsString());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        argsValues.forEach(arg -> new PlayCommand().playMusicFromSlash(slashEvent, arg, null, false, true));

        slashEvent.replyEmbeds(new EmbedBuilder().setColor(UtilityClass.getRandomColor()).setTitle("Multiple tracks queued")
                .setDescription(slashEvent.getOptions().size() <= 1 ? slashEvent.getOptions().size() + " song has been queued."
                        : slashEvent.getOptions().size() + " songs have been queued.").build()).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "add up to " + SONG_LIMIT_PER_COMMAND + " songs to the queue";
    }

    @Override
    public @NotNull String getName() {
        return "play_multiplesongs";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        List<OptionData> optionDataList = new ArrayList<>();
        for (int i = 0; i < SONG_LIMIT_PER_COMMAND; i++) {
            optionDataList.add(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(i),
                    i + ". song url or title", i == 0, false));
        }
        return optionDataList;
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        List<String> argList = new ArrayList<>();
        for (int i = 0; i < SONG_LIMIT_PER_COMMAND; i++) {
            argList.add(i + "_song-title-or-url");
        }
        return argList;
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

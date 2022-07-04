package commands.commands_slash;

import audioplayer.PlayCommand;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import library_class.LibraryClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MultipleSongsQueued implements ISlashCommands {
    public int songLimitPerCommand = 25;

    /**
     * adds as many songs as a user provided to the queue
     *
     * @param slashEvent {@link SlashCommandInteractionEvent}
     */
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
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

        slashEvent.replyEmbeds(new EmbedBuilder().setColor(LibraryClass.getRandomColor()).setTitle("Multiple tracks queued")
                .setDescription(slashEvent.getOptions().size() <= 1 ? slashEvent.getOptions().size() + " song has been queued."
                        : slashEvent.getOptions().size() + " songs have been queued.").build()).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "add up to " + songLimitPerCommand + " songs to the queue";
    }

    @Override
    public @NotNull String getName() {
        return "play_multiplesongs";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        List<OptionData> optionDataList = new ArrayList<>();
        for (int i = 0; i < songLimitPerCommand; i++) {
            optionDataList.add(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(i),
                    i + ". song url or title", i == 0, false));
        }
        return optionDataList;
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        List<String> argList = new ArrayList<>();
        for (int i = 0; i < songLimitPerCommand; i++) {
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
}

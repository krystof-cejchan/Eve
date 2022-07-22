package cz.krystofcejchan.commands.commands_slash.pause_resume;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.commands_slash.PlaySongSlash;
import cz.krystofcejchan.commands.purecommands.subparts.GetCurrentTrack;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class PauseTrack implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        if (GetCurrentTrack.getTrack(slashEvent.getGuild()) == null) {
            slashEvent.reply("There's no song to be paused *sad noises*; you can add one using '" +
                    new PlaySongSlash().getName() + "' command").setEphemeral(true).queue();
            return;
        }

        Logic.pauseOrResume(slashEvent.getGuild(), true);

        slashEvent.reply("Current track has been paused! ⏸️\nYou can resume the track using '"
                +new ResumeTrack().getName()+"' command").setEphemeral(false).queue();


    }


    @Override
    public @NotNull String getDescription() {
        return "Pauses the currently being played track";
    }

    @Override
    public @NotNull String getName() {
        return "pause";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.NONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return null;
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return null;
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

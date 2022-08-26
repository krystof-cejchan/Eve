package cz.krystofcejchan.commands.commands_slash.volume;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.enums_annotations_exceptions.enums.VolumeUpDown;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static cz.krystofcejchan.commands.purecommands.VolumePure.setVolumeUpOrDown;
import static cz.krystofcejchan.commands.purecommands.subparts.GetCurrentVolume.getVolume;

public class VolumeDownSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        int oldVol = getVolume(slashEvent.getGuild());
        setVolumeUpOrDown(slashEvent.getGuild(), VolumeUpDown.DOWN);
        slashEvent.replyEmbeds(VolumeCustomSLASH.embed.get(Objects.requireNonNull(slashEvent.getMember()),
                slashEvent.getGuild(), oldVol)).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "decrease volume by 10";
    }

    @Override
    public @NotNull String getName() {
        return "volumedown";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.NONE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return null;
    }

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



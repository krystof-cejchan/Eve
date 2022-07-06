package commands.commands_slash.volume;

import commands.commands_slash.ISlashCommands;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static commands.purecommands.VolumePure.setVolumeUpOrDown;
import static commands.purecommands.subparts.GetCurrentVolume.getVolume;
import static enums_annotations_exceptions.enums.VolumeUpDown.DOWN;

public class VolumeDownSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        int oldVol = getVolume(slashEvent.getGuild());
        setVolumeUpOrDown(slashEvent.getGuild(), DOWN);
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
    public @NotNull ArgumentSlashCommandCount takesArguments() {
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



package cz.krystofcejchan.commands.commands_slash.volume;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static cz.krystofcejchan.commands.purecommands.VolumePure.setVolumeTo;
import static cz.krystofcejchan.commands.purecommands.subparts.GetCurrentVolume.getVolume;

public class Mute implements ISlashCommands {
    protected static int beforeVolume = 0;

    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        beforeVolume = (int) getVolume(slashEvent.getGuild());

        setVolumeTo(slashEvent.getGuild(), 0);
        slashEvent.replyEmbeds(VolumeCustomSLASH.embed.get(Objects.requireNonNull(slashEvent.getMember()),
                slashEvent.getGuild(), beforeVolume)).queue();
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Mutes the bot";
    }

    @NotNull
    @Override
    public String getName() {
        return "mute";
    }

    @NotNull
    @Override
    public ArgumentSlashCommandCount takesArguments() {
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

    @NotNull
    @Override
    public List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.MUSIC);
    }
}

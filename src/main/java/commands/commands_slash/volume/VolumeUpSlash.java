package commands.commands_slash.volume;

import commands.commands_slash.ISlashCommands;
import enums_annotations_exceptions.enums.Arguments;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

import static commands.purecommands.VolumePure.setVolumeUpOrDown;
import static commands.purecommands.subparts.GetCurrentVolume.getVolume;
import static enums_annotations_exceptions.enums.VolumeUpDown.UP;

public class VolumeUpSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        int oldVol = getVolume(slashEvent.getGuild());
        setVolumeUpOrDown(slashEvent.getGuild(), UP);
        slashEvent.replyEmbeds(VolumeCustomSLASH.embed.get(Objects.requireNonNull(slashEvent.getMember()),
                slashEvent.getGuild(), oldVol)).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "increase volume by 10";
    }

    @Override
    public @NotNull String getName() {
        return "volumeup";
    }

    @Override
    public @NotNull Arguments takesArguments() {
        return Arguments.NONE;
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
        return false;
    }
}

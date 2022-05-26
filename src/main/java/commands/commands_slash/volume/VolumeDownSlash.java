package commands.commands_slash.volume;

import commands.commands_slash.ISlashCommands;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static commands._pure_commands.Volume_PURE.setVolumeUpOrDown;
import static commands._pure_commands.subparts.GetCurrentVolume.getVolume;
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
    public boolean takesArguments() {
        return false;
    }

    @Nullable
    @Override
    public OptionData getOptionData() {
        return null;
    }

    @Nullable
    @Override
    public String getArgName() {
        return null;
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }
}



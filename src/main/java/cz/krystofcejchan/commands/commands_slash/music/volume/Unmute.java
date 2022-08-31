package cz.krystofcejchan.commands.commands_slash.music.volume;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.purecommands.VolumePure;
import cz.krystofcejchan.commands.purecommands.subparts.GetCurrentVolume;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Unmute implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
          if (GetCurrentVolume.getVolume(slashEvent.getGuild()) != 0) {
            slashEvent.reply("Bot is not muted or at least the volume is not set to 0").setEphemeral(true).queue();
            return;
        }

        if (slashEvent.getOptions().size() < 1) {
            VolumePure.setVolumeTo(slashEvent.getGuild(), Mute.beforeVolume);
        } else {
            VolumePure.setVolumeTo(slashEvent.getGuild(), (int) slashEvent.getOptions().get(0).getAsDouble());
        }

        slashEvent.replyEmbeds(
                        VolumeCustomSLASH.embed.get(Objects.requireNonNull(slashEvent.getMember()),
                                slashEvent.getGuild(), 0))
                .queue();
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Unmutes the bot only if bot's been muted";
    }

    @NotNull
    @Override
    public String getName() {
        return "unmute";
    }

    @NotNull
    @Override
    public ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return Collections.singletonList(new OptionData(OptionType.NUMBER,
                Objects.requireNonNull(Objects.requireNonNull(getArgName()).get(0)),
                "set volume to x -  if left empty, volume will be set to the bot's volume before muted")
                .setRequiredRange(1, 200)
                .setRequired(false));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return Collections.singletonList("volume");
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

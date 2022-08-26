package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.audioplayer.GuildMusicManager;
import cz.krystofcejchan.audioplayer.PlayerManager;
import cz.krystofcejchan.commands.purecommands.subparts.GetCurrentTrack;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Destroy implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(slashEvent.getGuild()));
        if (GetCurrentTrack.getTrack(slashEvent.getGuild()) != null)
            musicManager.SCHEDULER.PLAYER.stopTrack();
        musicManager.SCHEDULER.QUEUE.clear();
        musicManager.SCHEDULER.PLAYER.destroy();

        slashEvent.replyEmbeds(new EmbedBuilder().setTitle("Stopped and destroyed")
                .addField("Audio has stopped, queue has been emptied, memory erased\uD83E\uDD16", "", false)
                .setColor(UtilityClass.getRandomColor()).build()).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "stops the audio and destroys current queue";
    }

    @Override
    public @NotNull String getName() {
        return "destroy_stop";
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

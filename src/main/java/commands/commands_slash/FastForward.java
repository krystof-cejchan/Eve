package commands.commands_slash;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import commands.purecommands.subparts.LetCurrentTrackFastForward;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import library_class.LibraryClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static audioplayer.NowPlayingCommand.getTimestamp;

public class FastForward extends LetCurrentTrackFastForward implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        final int posBefore = (int) Objects.requireNonNull(getTrack(slashEvent.getGuild())).getPosition();
        final int getArg_beforeDisappearing = Objects.requireNonNull(slashEvent
                        .getOption(Objects.requireNonNull(getArgName()).get(0)))
                .getAsInt() * 1000;
        int finish_code = fastForward(slashEvent.getGuild(), getArg_beforeDisappearing);

        switch (finish_code) {

            case 1 -> {
                AudioTrack currTrack = getTrack(slashEvent.getGuild());
                assert currTrack != null;
                String duration = getTimestamp(currTrack.getDuration());
                slashEvent.replyEmbeds(new EmbedBuilder().setColor(LibraryClass.getRandomColor())
                        .setTitle("Successfully fast-forwarded")
                        .setDescription(currTrack.getInfo().title)
                        .addField("Previous position", getTimestamp(posBefore) + "** / **" + duration, true)
                        .addField("Current position", getTimestamp(Long.parseLong(String
                                .valueOf(getArg_beforeDisappearing))) + "**/**" + duration, true)
                        .addField("Time Left",
                                getTimestamp(Long.parseLong(String.valueOf(
                                        currTrack.getDuration() - currTrack.getPosition()))), false)
                        .build()).queue();
            }
            case -1 -> slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.ORANGE)
                    .addField("No song to be fast-forwarded was found", "*to add a track, use " + new PlaySongSlash().getName() + " command*", true)
                    .build()).queue();
            case -2 -> slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.RED)
                    .addField("Error has occurred", "*Provide an integer between 0 and the song's duration*", true)
                    .build()).queue();
        }
    }

    @Override
    public @NotNull String getDescription() {
        return "Fast forward the current track";
    }

    @Override
    public @NotNull String getName() {
        return "fast-forward";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.INTEGER, Objects.requireNonNull(Objects
                .requireNonNull(getArgName()).get(0)),
                "amount of seconds the track will be fast-forwarded by",
                true)));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return Collections.singletonList("seconds-to-be-fastforwarded");
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

package cz.krystofcejchan.commands.commands_slash;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import cz.krystofcejchan.commands.purecommands.subparts.LetCurrentTrackFastForward;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
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

import static cz.krystofcejchan.utility_class.UtilityClass.getTimeStampMilliToStringTime;

public class FastForward extends LetCurrentTrackFastForward implements ISlashCommands {
    @Override
    public synchronized void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        final int posBefore = (int) Objects.requireNonNull(getTrack(slashEvent.getGuild())).getPosition();
        final int getArg_beforeDisappearing = Objects.requireNonNull(slashEvent
                        .getOption(Objects.requireNonNull(getArgName()).get(0)))
                .getAsInt() * 1000;
        int finish_code = fastForward(slashEvent.getGuild(), getArg_beforeDisappearing);

        switch (finish_code) {

            case 1 -> {
                AudioTrack currTrack = getTrack(slashEvent.getGuild());
                assert currTrack != null;
                String duration = getTimeStampMilliToStringTime(currTrack.getDuration());
                slashEvent.replyEmbeds(new EmbedBuilder().setColor(UtilityClass.getRandomColor())
                        .setTitle("Successfully fast-forwarded")
                        .setDescription(currTrack.getInfo().title)
                        .addField("Previous position", getTimeStampMilliToStringTime(posBefore) + "** / **" + duration, true)
                        .addField("Current position", getTimeStampMilliToStringTime(Long.parseLong(String
                                .valueOf(posBefore + getArg_beforeDisappearing))) + "**/**" + duration, true)
                        .addField("Time Left",
                                getTimeStampMilliToStringTime(Long.parseLong(String.valueOf(
                                        currTrack.getDuration() - (posBefore + getArg_beforeDisappearing)))), false)
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
    public @NotNull
    String getDescription() {
        return "Fast forward the current track";
    }

    @Override
    public @NotNull
    String getName() {
        return "fast-forward";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
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

    @Override
    public @NotNull
    List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.MUSIC);
    }
}

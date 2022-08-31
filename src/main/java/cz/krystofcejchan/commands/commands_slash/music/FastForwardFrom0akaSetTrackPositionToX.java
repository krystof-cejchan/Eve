package cz.krystofcejchan.commands.commands_slash.music;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.purecommands.subparts.LetCurrentTrackFastForwardFrom0;
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

public class FastForwardFrom0akaSetTrackPositionToX extends LetCurrentTrackFastForwardFrom0 implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        final int posBefore = (int) Objects.requireNonNull(getTrack(slashEvent.getGuild())).getPosition();
        final int getArg_beforeDisappearing = Objects.requireNonNull(slashEvent
                        .getOption(Objects.requireNonNull(getArgName()).get(0)))
                .getAsInt() * 1000;
        final int finish_code = fastForward(slashEvent.getGuild(), getArg_beforeDisappearing);

        switch (finish_code) {
            case 1 -> {
                String duration = UtilityClass.getTimeStampMilliToStringTime(Objects.requireNonNull(getTrack(slashEvent.getGuild())).getDuration());
                slashEvent.replyEmbeds(new EmbedBuilder().setColor(UtilityClass.getRandomColor())
                        .setTitle("Track position successfully changed")
                        .setDescription(Objects.requireNonNull(getTrack(slashEvent.getGuild())).getInfo().title)
                        .addField("Previous position", UtilityClass.getTimeStampMilliToStringTime(posBefore) + "** / **" + duration, true)
                        .addField("Current position", UtilityClass.getTimeStampMilliToStringTime(Long.parseLong(String
                                .valueOf(getArg_beforeDisappearing))) + "**/**" + duration, true).build()).queue();
            }
            case -1 -> slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.ORANGE)
                    .addField("No song was found", "*to add a track, use " + new PlaySongSlash().getName()
                            + " command*", true)
                    .build()).queue();
            case -2 -> slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.RED)
                    .addField("Error has occurred", "*Provide an integer between 0 and the song's duration*",
                            true)
                    .build()).queue();
        }
    }

    @Override
    public @NotNull String getDescription() {
        return "set position of currently-played track";
    }

    @Override
    public @NotNull String getName() {
        return "setTrackPosition";
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
                "paste an integer which will represent a specific second the track's position will be skipped to",
                true)));
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("time-in-seconds"));
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

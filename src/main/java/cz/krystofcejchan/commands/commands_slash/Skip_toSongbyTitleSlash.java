package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.commands.purecommands.SkipToTitle;
import cz.krystofcejchan.commands.purecommands.subparts.GetCurrentTrack;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static cz.krystofcejchan.commands.purecommands.subparts.GetUsersVoiceChannels.botsAudioChannel;
import static cz.krystofcejchan.commands.purecommands.subparts.GetUsersVoiceChannels.usersAudioChannel;

public class Skip_toSongbyTitleSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        if (GetCurrentTrack.getTrack(slashEvent.getGuild()) == null) {
            slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.RED).setTitle("That was a fail \uD83D\uDE23\uD83D\uDE23")
                            .addField("No song found", "There's nothing playing at the moment", true).build())
                    .queue();
            return;
        }
        SkipToTitle.skipToTrackByTitle(usersAudioChannel(Objects.requireNonNull(slashEvent.getMember())),
                botsAudioChannel(slashEvent.getGuild()),
                slashEvent.getGuild(), Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(Objects
                        .requireNonNull(getArgName()).get(0)))).getAsString());

        slashEvent.reply("Skipped to **" + Objects.requireNonNull(GetCurrentTrack.getTrack(slashEvent.getGuild()))
                .getInfo().title + "**").queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "skips to song by its title";
    }

    @Override
    public @NotNull String getName() {
        return "skiptosongbyname";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(Objects.requireNonNull(
                        getArgName())
                .get(0)), "title of the song to be skipped to  (discord does not allow to show more than 25 results)",
                true, true)));
    }

    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("tracktitle"));
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

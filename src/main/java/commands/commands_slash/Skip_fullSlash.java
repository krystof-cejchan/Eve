package commands.commands_slash;

import commands.purecommands.SkipPure;
import commands.purecommands.subparts.GetCurrentTrack;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Skip_fullSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        if (slashEvent.getOption(Objects.requireNonNull(Objects.requireNonNull(getArgName()).get(0))) != null) {
            SkipPure.skipTrackTo(Objects.requireNonNull(Objects.requireNonNull(slashEvent.getMember()).getVoiceState()).getChannel(),
                    Objects.requireNonNull(Objects.requireNonNull(slashEvent.getGuild()).getSelfMember()
                            .getVoiceState()).getChannel(), slashEvent.getGuild(),
                    Objects.requireNonNull(slashEvent.getOption(getArgName().get(0))).getAsInt());
        } else {
            SkipPure.skipTrackTo(Objects.requireNonNull(Objects.requireNonNull(slashEvent.getMember()).getVoiceState()).getChannel(),
                    Objects.requireNonNull(Objects.requireNonNull(slashEvent.getGuild()).getSelfMember()
                            .getVoiceState()).getChannel(), slashEvent.getGuild(),
                    null);
        }
        if (GetCurrentTrack.getTrack(slashEvent.getGuild()) == null)
            slashEvent.reply("Skipped to **void**; there is nothing playing right now").queue();
        else
            slashEvent.reply("Skipped to **" +
                    Objects.requireNonNull(GetCurrentTrack.getTrack(slashEvent.getGuild())).getInfo().title + "**").queue();

    }

    @Override
    public @NotNull String getDescription() {
        return "skips to a certain song by its index or if no index is provided then 1 track will be skipped";
    }

    @Override
    public @NotNull String getName() {
        return "skipto";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.INTEGER, Objects.requireNonNull(Objects.requireNonNull(getArgName()).get(0)),
                "index of the track you wish to skip to; if left empty, one track will be skipped", false)));
    }

    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("index"));
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

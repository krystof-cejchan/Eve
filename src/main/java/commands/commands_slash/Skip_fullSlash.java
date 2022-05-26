package commands.commands_slash;

import commands._pure_commands.Skip_PURE;
import commands._pure_commands.subparts.GetCurrentTrack;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Skip_fullSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        if (slashEvent.getOption(Objects.requireNonNull(getArgName())) != null) {
            Skip_PURE.skipTrackTo(Objects.requireNonNull(Objects.requireNonNull(slashEvent.getMember()).getVoiceState()).getChannel(),
                    Objects.requireNonNull(Objects.requireNonNull(slashEvent.getGuild()).getSelfMember()
                            .getVoiceState()).getChannel(), slashEvent.getGuild(),
                    Objects.requireNonNull(slashEvent.getOption(getArgName())).getAsInt());
        } else {
            Skip_PURE.skipTrackTo(Objects.requireNonNull(Objects.requireNonNull(slashEvent.getMember()).getVoiceState()).getChannel(),
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
        return "skips to a certain song by its index";
    }

    @Override
    public @NotNull String getName() {
        return "skiptoindex";
    }

    @Override
    public boolean takesArguments() {
        return true;
    }

    @Nullable
    @Override
    public OptionData getOptionData() {
        return new OptionData(OptionType.INTEGER, Objects.requireNonNull(getArgName()),
                "index of the track you wish to skip to; if left empty, one track will be skipped", false);
    }

    @Nullable
    @Override
    public String getArgName() {
        return "index";
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }
}

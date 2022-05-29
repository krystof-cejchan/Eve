package commands.commands_slash;

import commands.purecommands.SkipToTitle;
import commands.purecommands.subparts.GetCurrentTrack;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static commands.purecommands.subparts.GetUsersVoiceChannels.botsAudioChannel;
import static commands.purecommands.subparts.GetUsersVoiceChannels.usersAudioChannel;

public class Skip_toSongbyTitleSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        SkipToTitle.skipToTrackbyTitle(usersAudioChannel(Objects.requireNonNull(slashEvent.getMember())),
                botsAudioChannel(slashEvent.getGuild()),
                slashEvent.getGuild(), Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName())))
                        .getAsString());

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
    public boolean takesArguments() {
        return true;
    }

    @Nullable
    @Override
    public OptionData getOptionData() {
        return new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()),
                "title of the song to be skipped to  (discord does not allow to show more than 25 results)",
                true, true);
    }

    @Nullable
    @Override
    public String getArgName() {
        return "tracktitle";
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }
}

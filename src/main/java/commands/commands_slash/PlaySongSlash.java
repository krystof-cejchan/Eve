package commands.commands_slash;

import audioplayer.PlayCommand;
import enums_annotations_exceptions.annotations.Slash;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slash()
public class PlaySongSlash implements ISlashCommands {
    /**
     * Executes the command
     *
     * @param slashEvent {@link SlashCommandInteractionEvent}
     */
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {

       /* boolean finish = new Play_PURE().playMusic(slashEvent.getGuild(), GetUsersVoiceChannels.usersAudioChannel(Objects.requireNonNull(slashEvent.getMember())),
                GetUsersVoiceChannels.botsAudioChannel(slashEvent.getGuild()),
                Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()))).getAsString(), false);

        if (finish)
            slashEvent.reply("Track was added; **" + GetCurrentTrack.getTrack(slashEvent.getGuild()) + "**").queue();
        else
            slashEvent.reply("Track was not added successfully").queue();*/
        new PlayCommand().playMusicFromSlash(slashEvent, Objects.requireNonNull(slashEvent.getOption
                        (Objects.requireNonNull(getArgName()).get(0))).getAsString(),
                null);

    }

    /**
     * @return description of the command
     */
    @Override
    public @NotNull String getDescription() {
        return "Plays a track";
    }

    /**
     * @return name of the command
     */
    @Override
    public @NotNull String getName() {
        return "playsong";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    /**
     * options for slash commands
     *
     * @return OptionData
     */
    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.STRING, Objects.requireNonNull(Objects.requireNonNull(getArgName())
                .get(0)), "paste track url or title",
                true, false)));
    }

    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("song"));
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return true;
    }
}

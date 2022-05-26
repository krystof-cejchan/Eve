package commands.commands_slash;

import audio_player.PlayCommand;
import enums_annotations_exceptions.annotations.Slash;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
                        (Objects.requireNonNull(getArgName()))).getAsString(),
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
    public boolean takesArguments() {
        return true;
    }

    /**
     * options for slash commands
     *
     * @return OptionData
     */
    @Nullable
    @Override
    public OptionData getOptionData() {
        return new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()), "paste track url or title",
                true, false);
    }

    @Nullable
    @Override
    public String getArgName() {
        return "song";
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }
}

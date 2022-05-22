package commands._pure_commands.subparts;

import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GetUsersVoiceChannels {
    public static AudioChannel usersAudioChannel(@NotNull Member member) {
        return Objects.requireNonNull(member.getVoiceState()).getChannel();

    }

    public static AudioChannel botsAudioChannel(Guild guild) {

        return (Objects.requireNonNull(Objects.requireNonNull(guild).getSelfMember().getVoiceState())).getChannel();
    }

    public static void letBotJoinUser(@NotNull Member member, @NotNull Guild guild) {

        AudioManager audioManager = guild.getAudioManager();

        audioManager.openAudioConnection(usersAudioChannel(member));
    }

    public static void joinAudioChannel(@NotNull AudioChannel audioChannel, @NotNull Guild guild) {

        AudioManager audioManager = guild.getAudioManager();

        audioManager.openAudioConnection(audioChannel);
    }
}

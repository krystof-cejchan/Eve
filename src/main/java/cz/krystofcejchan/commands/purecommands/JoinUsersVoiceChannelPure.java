package cz.krystofcejchan.commands.purecommands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class JoinUsersVoiceChannelPure {
    public static void join(@NotNull Member member, Guild guild) {

        AudioChannel channel = Objects.requireNonNull(Objects.requireNonNull(member).getVoiceState()).getChannel();
        if (channel != null) {
            if (!Objects.requireNonNull(guild).getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT))
                return;


            AudioManager audioManager = guild.getAudioManager();

            audioManager.openAudioConnection(channel);

        }


    }
}

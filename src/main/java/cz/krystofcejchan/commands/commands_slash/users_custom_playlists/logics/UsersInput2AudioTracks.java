package cz.krystofcejchan.commands.commands_slash.users_custom_playlists.logics;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import cz.krystofcejchan.commands.purecommands.playsongcommand_pure.PlayerManagerPure;
import net.dv8tion.jda.api.entities.Guild;

import java.util.List;
import java.util.stream.Collectors;

public class UsersInput2AudioTracks {
    public static List<AudioTrackInfo> transformUsersInputToAudioTracks(Guild guild, List<String> inputList) {
        return inputList
                .stream()
                .flatMap(input -> PlayerManagerPure.getInstance().loadAndReturnAudioTracks(guild, input)
                        .stream()
                        .map(AudioTrack::getInfo))
                .collect(Collectors.toList());
    }

}

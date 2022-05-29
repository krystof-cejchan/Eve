package commands.purecommands.subparts;

import audioplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;

import java.util.ArrayList;

public class GetWholePlaylist {

    public static ArrayList<AudioTrack> getPlayList(Guild guild) {

        return new ArrayList<>(PlayerManager.getInstance().getMusicManager(guild).SCHEDULER.QUEUE);
    }

    public static ArrayList<String> getSongsTitles(Guild guild) {
        //GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(guild);
        //BlockingQueue<AudioTrack> queue = musicManager.SCHEDULER.QUEUE;
        ArrayList<String> list = new ArrayList<>();
        for (AudioTrack a : getPlayList(guild)) {
            System.out.println(a.getInfo().title);
            list.add(a.getInfo().title);
        }

        return list;
    }
}

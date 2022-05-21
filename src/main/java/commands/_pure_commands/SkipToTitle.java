package commands._pure_commands;

import audio_player.PlayerManager;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Guild;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class SkipToTitle {
    public static void skipToTrackbyTitle(AudioChannel user, AudioChannel bot, Guild guild, String usersInput) {
        try {
            BlockingQueue<AudioTrack> queue = PlayerManager.getInstance().getMusicManager(guild).SCHEDULER.QUEUE;

            if (!queue.isEmpty()) {
                if (queue.stream().anyMatch(audioTrack -> audioTrack.getInfo().title.equals(usersInput))) {
                    System.out.println("true");

                    Skip_PURE.skipTrackTo(user, bot, guild, getSongIndex(new ArrayList<>(queue), usersInput) + 1);

                }

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static int getSongIndex(ArrayList<AudioTrack> list, String title) throws NullPointerException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getInfo().title.equalsIgnoreCase(title)) return i;
        }
        throw new NullPointerException(title + " is not included in the list");
    }
}

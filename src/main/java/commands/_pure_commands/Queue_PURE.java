package commands._pure_commands;

import _library_class.GlobalValues;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import commands._pure_commands.subparts.GetCurrentTrack;
import commands._pure_commands.subparts.GetWholePlaylist;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;

import java.util.ArrayList;
import java.util.Objects;

public class Queue_PURE {
    public static Message getQueueAndReturnItAsReadyMessage(Guild guild) {
        MessageBuilder msgBuilder = new MessageBuilder();
        ArrayList<AudioTrack> queue = new ArrayList<>(GetWholePlaylist.getPlayList(guild));

        if (queue.size() < 1 && GetCurrentTrack.getTrack(guild) != null) {
            return msgBuilder.append("There's only one song playing right now : **")
                    .append(String.valueOf(Objects.requireNonNull(GetCurrentTrack.getTrack(guild)).getInfo().title))
                    .append("**").build();
        }
        if (!queue.isEmpty()) {

            int shownTrackCount = GlobalValues.shownTrackCount;
            final int trackCount = Math.min(queue.size(), shownTrackCount);
            final ArrayList<AudioTrack> trackList = new ArrayList<>(queue);

            msgBuilder.append("**QUEUE**\n");


            msgBuilder.append("__").append("Currently playing").append("__").append(":").append(" `")
                    .append(Objects.requireNonNull(GetCurrentTrack.getTrack(guild)).getInfo().title).append("` \n");
            for (int i = 0; i < trackCount; i++) {
                AudioTrack track = trackList.get(i);
                AudioTrackInfo info = track.getInfo();
                int ii = i + 1;
                msgBuilder.append("*").append(String.valueOf(ii)).append("*").append(".").append(" `")
                        .append(info.title).append("` \n");
            }

            int restCountTrack = trackList.size() - shownTrackCount;
            String trackSmore;

            if (trackList.size() > shownTrackCount) {
                if (restCountTrack < 2) {
                    trackSmore = " more track";
                } else {
                    trackSmore = " more tracks";
                }
                msgBuilder.append("\n+").append(String.valueOf(restCountTrack)).append(trackSmore);

                return msgBuilder.build();

            } else {
                return msgBuilder.build();
            }

        }
        return msgBuilder.append("Queue is empty   ✨***void*** ✨").build();
    }
}

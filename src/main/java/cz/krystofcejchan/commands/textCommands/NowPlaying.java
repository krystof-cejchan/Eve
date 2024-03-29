package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.audioplayer.NowPlayingCommand;
import cz.krystofcejchan.commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command informs you about the currently playing track</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class NowPlaying implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        nowPlaying(event);

    }

    private void nowPlaying(MessageReceivedEvent event) {
        try {
            NowPlayingCommand np = new NowPlayingCommand();
            np.getNowPlayingTrack(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {

        return "Now Playing";
    }

    @Override
    public String whatDoIDo() {

        return "This command informs you about the currently playing track";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("np");
        t.add("nowplaying");
        return t;
    }


}

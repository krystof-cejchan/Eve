package commands;

import audio_player.StopCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
/**
 * <p style="background-color:Green; color:Black">This command forces the bot to stop playing current song, delete the whole queue, but remain in the voice channel</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Stop implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        try {
            StopCommand stop = new StopCommand();
            stop.stopMusic(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Stop the tracks";
    }

    @Override
    public String whatDoIDo() {
        return "This command forces the bot to stop playing current song, delete the whole queue, but remain in the voice channel";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("stop");
        t.add("destroy");
        return t;
    }

}

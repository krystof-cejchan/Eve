package commands.textCommands;

import audio_player.VolumeCommand;
import commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command unmutes the bot</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Unmute implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        try {
            VolumeCommand volume = new VolumeCommand();
            volume.unmute(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {
        return "Unmute";
    }

    @Override
    public String whatDoIDo() {
        return "This command unmutes the bot";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("unmute");
        return t;
    }

}

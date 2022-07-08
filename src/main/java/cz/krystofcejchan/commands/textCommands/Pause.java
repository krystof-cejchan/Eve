package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.audioplayer.StopCommand;
import cz.krystofcejchan.commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command pauses the currently playing song</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Pause implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        try {
            StopCommand stop = new StopCommand();
            stop.pauseMusic(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {

        return "Pause a track";
    }

    @Override
    public String whatDoIDo() {

        return "This command pauses the currently playing song";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("pause");
        return t;
    }


}

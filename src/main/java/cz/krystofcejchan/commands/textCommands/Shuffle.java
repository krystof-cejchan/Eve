package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.audioplayer.ShuffleCommand;
import cz.krystofcejchan.commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command shuffles all tracks in the queue</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Shuffle implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        ShuffleCommand shuffle = new ShuffleCommand();
        shuffle.getShuffle(event);
    }

    @Override
    public String getName() {
        return "Shuffle";
    }

    @Override
    public String whatDoIDo() {
        return "This command shuffles all tracks in the queue";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("mix");
        t.add("shuffle");

        return t;
    }


}

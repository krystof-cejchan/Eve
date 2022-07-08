package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.audioplayer.QueueCommand;
import cz.krystofcejchan.commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command informs you about the upcoming tracks</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Queue implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        QueueCommand q = new QueueCommand();
        q.getQueue(event);

    }

    @Override
    public String getName() {

        return "Queue";
    }

    @Override
    public String whatDoIDo() {

        return "This command informs you about the upcoming tracks";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("q");
        t.add("queue");
        t.add("list");
        return t;
    }


}

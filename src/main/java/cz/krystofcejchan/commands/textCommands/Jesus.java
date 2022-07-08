package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.commands.ICommands;
import cz.krystofcejchan.commands.commands_others.Asian;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command sends you swiftly to Jesus</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Jesus implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        event.getMessage().reply(Asian.sendJesus()).queue();
    }

    @Override
    public String getName() {

        return "I will Send you to Jesus";
    }

    @Override
    public String whatDoIDo() {

        return "This command sends you swiftly to Jesus";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("jesus");
        t.add("js");

        return t;
    }


}

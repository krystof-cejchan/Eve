package commands.textCommands;

import commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">I'll tell you somethin' nice</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _SaySomethingNice implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        event.getMessage().reply("I like your nice blue eyes whether you've got em blue or not").queue();

    }

    @Override
    public String getName() {

        return "Say Something nice";
    }

    @Override
    public String whatDoIDo() {

        return "I'll tell you somethin' nice";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("ssn");
        return t;
    }

}

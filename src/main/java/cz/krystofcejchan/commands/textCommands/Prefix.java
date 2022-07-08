package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command changes the default prefix</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Prefix implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        // Prefix pref = new Prefix();

        try {

            if (event.getMessage().getContentRaw().split(" ")[1].length() >= 1) {
                cz.krystofcejchan.main.Prefix.setValue(event.getMessage().getContentRaw().split(" ")[1]);

                event.getMessage().reply("Prefix has been changed to " + cz.krystofcejchan.main.Prefix.getValue()).queue();
            }
        } catch (Exception ignored) {
        }

    }

    @Override
    public String getName() {

        return "Change Prefix";
    }

    @Override
    public String whatDoIDo() {

        return "This command changes the default prefix";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("prefix");
        t.add("pref");
        t.add("pf");

        return t;
    }


}

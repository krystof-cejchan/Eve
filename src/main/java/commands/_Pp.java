package commands;

import _library_class.LibraryClass;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
/**
 * <p style="background-color:Green; color:Black">This command guesses your "length"</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Pp implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        int len = LibraryClass.generateRandomInt(-1, 50);
        if (args.length <= 1) {
            event.getMessage().reply("pp = " + len + "cm.").queue();
        }

    }

    @Override
    public String getName() {

        return "Weenie";
    }

    @Override
    public String whatDoIDo() {

        return "This command guesses your \"length\"";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("pp");
        t.add("dick");
        t.add("weenie");
        return t;
    }

}

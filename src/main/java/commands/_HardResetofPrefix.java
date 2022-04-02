package commands;

import main.Prefix;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p style="background-color:Green;">This command hard-resets current prefix to ";" </p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _HardResetofPrefix implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) throws Exception {

        Prefix.setValue(";");
        event.getMessage().reply("The prefix has been reseted to " + Prefix.getValue()).queue();

    }

    @Override
    public String getName() {

        return "Prefix Hard-Reset";
    }

    @Override
    public String whatDoIDo() {

        return "This command hard-resets current prefix to \";\" ";
    }

    @Override
    public ArrayList<String> getTriggers() {


        return new ArrayList<>(Arrays.asList("prefreset", "prefixreset"));
    }

}

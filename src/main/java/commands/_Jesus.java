package commands;

import commands.commands_others.Asian;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class _Jesus implements ICommands {

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

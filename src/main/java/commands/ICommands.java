package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public interface ICommands {
    void doTask(MessageReceivedEvent event) throws Exception;

    String getName();

    String whatDoIDo();

    ArrayList<String> getTriggers();
}

package Commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface ICommands {
	void doTask(MessageReceivedEvent event);

	String getName();

	String whatDoIDo();

	ArrayList<String> getTriggers();
}

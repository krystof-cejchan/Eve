package commands_voice;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface IListeningCommands {

	void doTask(MessageReceivedEvent event) throws Exception;

	String getName();

	String whatDoIDo();

	ArrayList<String> getTags();
}

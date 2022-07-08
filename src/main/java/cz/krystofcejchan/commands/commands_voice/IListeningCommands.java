package cz.krystofcejchan.commands.commands_voice;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;

public interface IListeningCommands {

    void doTask(MessageReceivedEvent event, @Nullable String usersInput) throws Exception;

    String getName();

    String whatDoIDo();

    Boolean isParamRequired();

    ArrayList<String> getTags();

}

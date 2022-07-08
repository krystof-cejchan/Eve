package cz.krystofcejchan.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * Interface text commands implement
 *
 * @author krystof-cejchan
 */
public interface ICommands {
    /**
     * execute the command
     *
     * @param event {@link MessageReceivedEvent} to be able to send messages to the text channel
     * @throws Exception prevention of all problems code may encounter
     */
    void doTask(MessageReceivedEvent event) throws Exception;

    /**
     * @return name of the command
     */
    String getName();

    /**
     * @return short description of what the command does
     */
    String whatDoIDo();

    /**
     * @return what triggers executing this command
     */
    ArrayList<String> getTriggers();


}

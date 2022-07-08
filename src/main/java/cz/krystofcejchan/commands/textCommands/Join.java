package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.commands.ICommands;
import cz.krystofcejchan.main.VoiceChannels;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command forces the bot to join a voice channel. Write the title of the voice channel, if you don't want the bot to join you.</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Join implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        try {
            VoiceChannels joinVC = new VoiceChannels();
            if (args.length > 1) {
                joinVC.JoinChannel(event, args[1]);

            } else {
                System.out.println("empty");
                joinVC.Join(event);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {

        return "Join a voice channel";
    }

    @Override
    public String whatDoIDo() {

        return "This command forces the bot to join a voice channel. Write the title of the voice channel, if you don't want the bot to join you.";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("join");
        return t;
    }


}

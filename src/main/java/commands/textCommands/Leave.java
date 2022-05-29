package commands.textCommands;

import audioplayer.StopCommand;
import commands.ICommands;
import library_class.LibraryClass;
import main.VoiceChannels;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command forces the bot to leave your voice channel and discard the song queue</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Leave implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        VoiceChannels leaveVC = new VoiceChannels();
        StopCommand stop = new StopCommand();
        stop.stopMusic(event);
        leaveVC.Leave(event);
        LibraryClass.addReactionToTheMsg(event, "U+1F44B");

    }

    @Override
    public String getName() {

        return "Leave a voice channel";
    }

    @Override
    public String whatDoIDo() {

        return "This command forces the bot to leave your voice channel and discard the song queue";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("leave");
        t.add("exit");
        t.add("quit");
        return t;
    }


}

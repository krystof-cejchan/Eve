package commands.textCommands;

import audio_player.ResumeCommand;
import commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command resumes the currently paused song</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Resume implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        try {
            ResumeCommand resume = new ResumeCommand();
            resume.resumeMusic(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {

        return "Resume current song, if paused";
    }

    @Override
    public String whatDoIDo() {

        return "This command resumes the currently paused song";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("resume");
        t.add("continue");
        return t;
    }


}

package commands;

import java.util.ArrayList;

import audio_player.ResumeCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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

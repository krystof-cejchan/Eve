package commands;

import audio_player.StopCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class _Pause implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        try {
            StopCommand stop = new StopCommand();
            stop.pauseMusic(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {

        return "Pause a track";
    }

    @Override
    public String whatDoIDo() {

        return "This command pauses the currently playing song";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("pause");
        return t;
    }

}

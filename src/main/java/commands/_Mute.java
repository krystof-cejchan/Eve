package commands;

import audio_player.VolumeCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class _Mute implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        try {
            VolumeCommand volume = new VolumeCommand();
            volume.mute(event);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {

        return "Mute";
    }

    @Override
    public String whatDoIDo() {

        return "This command mutes the bot, but the currently playing song will carry on playing!";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("mute");
        return t;
    }

}

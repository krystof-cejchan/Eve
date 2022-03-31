package commands_voice;

import java.util.ArrayList;
import java.util.Arrays;

import audio_player.SkipCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Skip_toNext implements IListeningCommands {

    @Override
    public void doTask(MessageReceivedEvent event) throws Exception {
        try {
            SkipCommand skip = new SkipCommand();

            skip.skipTrack(event, true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {
        return "Skip to the next song";
    }

    @Override
    public String whatDoIDo() {
        return "This command skips one track in the queue";
    }

    @Override
    public ArrayList<String> getTags() {

        return new ArrayList<>(Arrays.asList

                ("skip", "skip one song", "next song"));
    }

}

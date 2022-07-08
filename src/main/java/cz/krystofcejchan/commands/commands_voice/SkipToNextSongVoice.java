package cz.krystofcejchan.commands.commands_voice;

import cz.krystofcejchan.audioplayer.SkipCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class SkipToNextSongVoice implements IListeningCommands {

    @Override
    public void doTask(MessageReceivedEvent event, String usersInput) throws Exception {
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
    public Boolean isParamRequired() {
        return false;
    }

    @Override
    public ArrayList<String> getTags() {

        return new ArrayList<>(Arrays.asList

                ("skip", "skip one song", "next song"));
    }

}

package commands;

import _library_class.LibraryClass;
import audio_player.MessageTypes;
import audio_player.PlayCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class _Play implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        PlayCommand playC = new PlayCommand();
        StringBuilder urlOrSearchKey = new StringBuilder();

        if (LibraryClass.isLink(args[1])) {
            playC.playMusic(event, args[1], true, MessageTypes.REG_MESSAGE, null);

        } else {
            for (int i = 1; i < args.length; i++) {
                urlOrSearchKey.append(args[i]).append(" ");

            }
            playC.playMusic(event, urlOrSearchKey.toString(), false, MessageTypes.REG_MESSAGE, null);
        }

    }

    @Override
    public String getName() {

        return "Play a track";
    }

    @Override
    public String whatDoIDo() {

        return "This command adds a song to the tracklist";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("play");
        t.add("p");
        return t;
    }

}

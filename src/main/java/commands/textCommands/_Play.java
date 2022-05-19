package commands.textCommands;

import _library_class.LibraryClass;
import audio_player.PlayCommand;
import commands.ICommands;
import enums_and_annotations.enums.MessageTypes;
import main.VoiceChannels;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command adds a song to the track list</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Play implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args.length <= 1) {
            new VoiceChannels().Join(event);
            return;
        }
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

        return "This command adds a song to the track list";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("play");
        t.add("p");
        return t;
    }


}

package commands;

import _library_class.LibraryClass;
import audio_player.PlayQCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
/**
 * <p style="background-color:Green;">This command adds every song from your playlist to the queue</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _PlayQueue implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        try {

            PlayQCommand pq = new PlayQCommand();

            if (LibraryClass.isLink(args[1])) {
                pq.playMusic(event, args[1], true);

            } else {
                event.getChannel().sendMessage("Please, provide a proper YouTube link").queue();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {

        return "Play a Queue";
    }

    @Override
    public String whatDoIDo() {

        return "This command adds every song from your playlist to the queue";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("playqueue");
        t.add("playq");
        t.add("pq");
        return t;
    }

}

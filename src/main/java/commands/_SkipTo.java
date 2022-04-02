package commands;

import audio_player.SkipCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
/**
 * <p style="background-color:Green;">This command skips to *x.* in the queue</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _SkipTo implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        try {
            if (args.length > 1) {
                SkipCommand s = new SkipCommand();
                s.skipTrackTo(event, Integer.parseInt(args[1]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return "Skip To *x.* song in the queue";
    }

    @Override
    public String whatDoIDo() {
        return "This command skips to *x.* in the queue";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("skipto");
        t.add("getto");
        return t;
    }

}

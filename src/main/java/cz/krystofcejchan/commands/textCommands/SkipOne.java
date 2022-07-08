package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.audioplayer.NowPlayingCommand;
import cz.krystofcejchan.audioplayer.SkipCommand;
import cz.krystofcejchan.commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command skips one track in the queue</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class SkipOne implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        try {
            SkipCommand skip = new SkipCommand();
            if (args.length > 1) {
                for (int i = 0; i < Integer.parseInt(args[1]); i++) {
                    skip.skipTrack(event, false);
                }
                event.getChannel()
                        .sendMessage("Successfully skipped to: \n**" + new NowPlayingCommand()
                                .getNpAudioTrack(event).getInfo().title + "**")
                        .queue();
            } else {

                skip.skipTrack(event, true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {
        return "Skip one song";
    }

    @Override
    public String whatDoIDo() {
        return "This command skips one track in the queue";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("skip");
        t.add("next");
        return t;
    }


}

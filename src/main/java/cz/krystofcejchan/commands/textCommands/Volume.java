package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.audioplayer.VolumeCommand;
import cz.krystofcejchan.commands.ICommands;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command changes current volume of the bot</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class Volume implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        try {
            VolumeCommand volume = new VolumeCommand();
            if (args.length > 1) {

                if (args[1].equalsIgnoreCase("up")) {

                    volume.upDownVolume(event, "UP");
                    return;
                }
                if (args[1].equalsIgnoreCase("down")) {

                    volume.upDownVolume(event, "DOWN");
                } else {
                    volume.setVolume(event, Integer.parseInt(args[1]));
                }

            } else {

                event.getMessage().reply("Current Volume is `" + volume.getCurrentVolume(event)
                        + "`.\nTo change Volume, use volume <number 1 - 200>").queue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {
        return "Volume";
    }

    @Override
    public String whatDoIDo() {
        return "This command changes current volume of the bot";
    }

    @Override
    public ArrayList<String> getTriggers() {
        ArrayList<String> t = new ArrayList<>();
        t.add("vol");
        t.add("volume");
        return t;
    }


}

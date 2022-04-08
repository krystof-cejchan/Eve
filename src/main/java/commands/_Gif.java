package commands;

import commands.commands_others.GifSender;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This Command sends you a gif depending on your keyword</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Gif implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");
        StringBuilder searchKey = new StringBuilder();

        try {
            if (args.length > 1) {

                for (int i = 1; i < args.length; i++) {
                    searchKey.append("%20").append(args[i]);
                }

            } else {

                searchKey = new StringBuilder("null");

            }

            GifSender gifs = new GifSender();
            String msg = gifs.call_me(searchKey.toString(), "2");

            event.getMessage().reply(msg).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getName() {

        return "Gif Sender";
    }

    @Override
    public String whatDoIDo() {

        return "This Command sends you a gif depending on your keyword";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("g");
        t.add("gif");
        t.add("movingpicture");
        return t;
    }

}

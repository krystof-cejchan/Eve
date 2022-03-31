package commands;

import commands.commands_others.Asian;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;

public class _EmotionalDamage implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {


        event.getMessage().reply(Asian.sendEmotionalDamage()).queue();
    }

    @Override
    public String getName() {

        return "Emotional Damage";
    }

    @Override
    public String whatDoIDo() {

        return "This command sends you one quick emotional damage";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("ed");
        t.add("emotionaldamage");

        return t;
    }

}

package commands;

import java.awt.Color;
import java.util.ArrayList;

import commands.commands_others.Birthday;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Me implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        // User user = event.getMessage().getAuthor();
        net.dv8tion.jda.api.entities.Member user = event.getMember();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        // embedBuilder.setAuthor(user.getNickname(), user.getAsMention(), null);
        embedBuilder.setColor(new Color(255, 215, 0));
        assert user != null;
        embedBuilder.addField("UserName", user.getAsMention(), false);
        embedBuilder.addField("ID", user.getId(), false);
        embedBuilder.addField("Account birthday 🎂🎂🎂",
                Birthday.getNormalDate(user.getTimeCreated()) + "\n" + Birthday.getNormalTime(user.getTimeCreated()),
                false);
        embedBuilder.setImage(user.getAvatarUrl());

        event.getMessage().replyEmbeds(embedBuilder.build()).queue();
        embedBuilder.clear();

    }

    @Override
    public String getName() {

        return "Who are you";
    }

    @Override
    public String whatDoIDo() {

        return "This command tells you your account details";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("me");
        return t;
    }

}

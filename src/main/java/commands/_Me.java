package commands;

import java.awt.Color;
import java.util.ArrayList;

import commands_others.Birthday;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Me implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {
		// TODO Auto-generated method stub
		User user = event.getAuthor();
		EmbedBuilder embedBuilder = new EmbedBuilder();
		embedBuilder.setAuthor(user.getName(), user.getAsMention(), user.getAvatarUrl());
		embedBuilder.setColor(new Color(255, 215, 0));
		embedBuilder.addField("UserName", user.getAsTag(), false);
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
		// TODO Auto-generated method stub
		return "Who are you";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command tells you your account details";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("me");
		return t;
	}

}

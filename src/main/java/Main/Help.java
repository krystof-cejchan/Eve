package Main;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends ListenerAdapter {

	public void HelpCommand(MessageReceivedEvent event) {

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("dostaneš flákanec");
		embed.setColor(Color.decode("#4dff17"));
		embed.setDescription("commands: (prefix: **'** )");

		embed.addField("Help", "**'**help", false);

		embed.addField("Say Something Nice", "**'**ssn", false);

		embed.addField("Who the fuck am I?", "**'**me", false);

		embed.addField("Who's in change?", "**'**cpt", false);

		embed.addField("Server Info", "**'**ServerInfo", false);

		embed.addField("Moving pictures", "**'**gif *keyword*", false);

		embed.addField("A$$", "**'**send ass", false);

		embed.addField(" o )  o)", "**'**send nudes", false);

		embed.setImage("http://kys.hys.cz/Z0c9owbRc0OnCeQVHdY3/images/pika%20pika%20with%20hat.png");
		embed.setFooter("*The End*");

	//	event.getMessage().reply(embed.build()).queue();
		event.getMessage().replyEmbeds(embed.build()).queue();
		embed.clear();
	}

}

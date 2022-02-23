package commands_others;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class inProgress {
	public void sentInProgress(MessageReceivedEvent event) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("...coming soon...");
		embed.setColor(Color.decode("#7987bd"));
		embed.setAuthor("Večerníček#3533");
		embed.addField("List:", "Spotify as Music Source \n"
				+ "Youtube Queue Bug fix\n"
				+ "FastForwarding\n"
				+ "\n"
			/*	+ "\n"
				+ "\n"
				+ "\n"*/
				+ "...\n"
				
				, false);
		

		event.getMessage().replyEmbeds(embed.build()).queue();
		embed.clear();
	}

}

package commands.commands_others;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class inProgress {
	public void sentInProgress(MessageReceivedEvent event) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("...coming soon...");
		embed.setColor(Color.decode("#7987bd"));
		embed.setAuthor("Večerníček#3533");
		/*
		 * + "\n" + "\n" + "\n"
		 */
		embed.addField("List:", """
						Spotify as Music Source\s
						Youtube Queue Bug fix
						FastForwarding

						...
						"""

				, false);

		event.getMessage().replyEmbeds(embed.build()).queue();
		embed.clear();
	}

}

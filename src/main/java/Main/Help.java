package Main;

import java.awt.Color;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends ListenerAdapter {

	public void HelpCommand(MessageReceivedEvent event) {

		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("General");
		embed.setColor(Color.decode("#4dff17"));
		embed.setDescription("(prefix is **'**)");

		embed.addField("Show Commands", "**'**help", false);

		embed.addField("Contact us 24 hours a day 7 days a week 🙂", "**'**support <your message>", false);
		embed.addField("See what is coming soon", "**'**inprogress", false);

		// embed.addField("Say Something Nice", "**'**ssn", false);

		embed.addField("Who the fuck am I?", "**'**me", false);

		embed.addField("Who's in charge?", "**'**cpt", false);

		embed.addField("Server Info", "**'**ServerInfo", false);

		embed.addField("Moving pictures", "**'**gif <keyword>", false);

		embed.addField("Emotional Damage", "**'**ed *or*   **'**emotional damage", false);
		embed.addField("I will send you to Jesus", "**'**jesus", false);

		embed.addBlankField(false);

		embed.addField("Music", "", false);
		embed.addField("Play song", "**'**join *or*   **'**join <Channel name>", false);
		embed.addField("Play song", "**'**p *or*   **'**play <youtube link / searchkey>", false);
		embed.addField("Play music list", "**'**pq *or*   **'**playqueue <youtube link>", false);
		embed.addField("Skip a music track",
				"**'**next *or*   **'**skip *OR*   **'**next *or*   **'**skip <amount of songs>", false);
		embed.addField("Skip queue to *X* song", "**'**skipto *or*   **'**getto <track number from the queue>", false);
		embed.addField("Delete a track from the queue", "**'**delete *or*   **'**remove <track number from the queue>",
				false);
		embed.addField("Show current queue", "**'**q *or*   **'**queue", false);
		embed.addField("Current Song", "**'**np *or*   **'**now playing", false);
		embed.addField("Pause music bot", "**'**pause *or*   **'**stop", false);
		embed.addField("Resume music bot", "**'**resume *or*   **'**continue", false);
		embed.addField("Mute", "**'**mute", false);
		embed.addField("Unmute", "**'**unmute", false);
		embed.addField("Set Volume", "**'**vol *or*   **'**volume <number 1-200>", false);
		embed.addField("Shuffle current queue", "**'**mix *or*   **'**shuffle", false);
		embed.addField("Leave", "**'**leave", false);

		event.getMessage().replyEmbeds(embed.build()).queue();
		embed.clear();
	}

}

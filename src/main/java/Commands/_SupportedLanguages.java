package Commands;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import LIBRARYclass.LibraryClass;
import Speech_Texts_Listening.LANGUAGES;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _SupportedLanguages implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) {

		ArrayList<LANGUAGES> langArr = new ArrayList<LANGUAGES>();
		for (LANGUAGES language : LANGUAGES.values()) {
			langArr.add(language);
		}
		Collections.sort(langArr);
		// MessageBuilder msgB = new MessageBuilder();
		EmbedBuilder embedBuilder = new EmbedBuilder();
		for (int i = 0; i < langArr.size(); i++) {
			// msgB.append((String.valueOf(i)) + "**" +
			// langArr.get(i).toString().toUpperCase() + "**");

			// msgB.append(LANGUAGES.getLangFlag(langArr.get(i)) + "\n");
			embedBuilder.addField(LANGUAGES.getLangFlag(langArr.get(i)), LANGUAGES.getProperLanguage(langArr.get(i)),
					true);

		}
		// event.getChannel().sendMessage(msgB.build()).queue();
		embedBuilder.setColor(LibraryClass.getRandomColor());
		embedBuilder.setTimestamp(Instant.now());
		event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Supported Languages";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command sends a list of all supported languages that bot is able to understand";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> tags = new ArrayList<>(Arrays.asList

		("language"));

		return tags;
	}

}

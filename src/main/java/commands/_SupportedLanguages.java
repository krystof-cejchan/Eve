package commands;

import _library_class.LibraryClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import voice_and_listening.LANGUAGES;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _SupportedLanguages implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        ArrayList<LANGUAGES> langArr = new ArrayList<>(Arrays.asList(LANGUAGES.values()));
        Collections.sort(langArr);
        // MessageBuilder msgB = new MessageBuilder();
        EmbedBuilder embedBuilder = new EmbedBuilder();
        for (LANGUAGES languages : langArr) {
            embedBuilder.addField(LANGUAGES.getLangFlag(languages), LANGUAGES.getProperLanguage(languages),
                    true);

        }
        // event.getChannel().sendMessage(msgB.build()).queue();
        embedBuilder.setColor(LibraryClass.getRandomColor());

        event.getChannel().sendMessageEmbeds(embedBuilder.build()).queue();

    }

    @Override
    public String getName() {
        return "Supported Languages";
    }

    @Override
    public String whatDoIDo() {
        return "This command sends a list of all supported languages that bot is able to understand";
    }

    @Override
    public ArrayList<String> getTriggers() {

        return new ArrayList<>(List.of("language"));
    }

}

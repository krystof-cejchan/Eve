package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.commands.ICommands;
import cz.krystofcejchan.enums_annotations_exceptions.enums.LANGUAGES.LANGUAGES;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p style="background-color:Green; color:Black">This command sends a list of all supported languages that bot is able to understand</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class SupportedLanguages implements ICommands {

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
        embedBuilder.setColor(UtilityClass.getRandomColor());

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

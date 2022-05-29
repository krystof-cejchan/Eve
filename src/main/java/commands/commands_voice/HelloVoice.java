package commands.commands_voice;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class HelloVoice implements IListeningCommands {


    @Override
    public void doTask(MessageReceivedEvent event, String usersInput) {

        event.getMessage().reply("Hello " + Objects.requireNonNull(event.getGuild().getMember(event.getAuthor())).getNickname()).queue();
    }

    @Override
    public String getName() {
        return "Hello";
    }

    @Override
    public String whatDoIDo() {
        return "This command answers a simple hello";
    }

    @Override
    public Boolean isParamRequired() {
        return false;
    }

    @Override
    public ArrayList<String> getTags() {

        return new ArrayList<>(Arrays.asList

                ("hello", "hi", "hey"));
    }

}

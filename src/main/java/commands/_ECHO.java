package commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import voice.voice_and_listening.SpeechToText;

import java.util.ArrayList;

public class _ECHO implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) {

        try {
            event.getMessage().reply("I'm listening...").queue();
            SpeechToText speechToText = new SpeechToText();
            speechToText.onEchoCommand(event);
        } catch (Exception ignored) {

        }
    }

    @Override
    public String getName() {

        return "Say a Voice Command";
    }

    @Override
    public String whatDoIDo() {

        return "This command joins Eve to your voice channel who will listen to your voice command for a short period of time";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("hey");
        t.add("hey eve");
        t.add("listen");
        return t;
    }

}

package cz.krystofcejchan.commands.textCommands;

import cz.krystofcejchan.commands.ICommands;
import cz.krystofcejchan.main.after_startup.AddingSlashCommandsToGuilds;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.List;

public class UploadSlash implements ICommands {
    @Override
    public void doTask(MessageReceivedEvent event) throws Exception {
        AddingSlashCommandsToGuilds.addSlashCommandsToSpecificGuild(event.getGuild());
    }

    @Override
    public String getName() {
        return "Upload Slashcommands";
    }

    @Override
    public String whatDoIDo() {
        return "this commands uploads all slash commands to your server manually";
    }

    @Override
    public ArrayList<String> getTriggers() {
        return new ArrayList<>(List.of("slashupload", "slash"));
    }
}

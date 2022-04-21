package commands;

import _library_class.LibraryClass;
import main.Prefix;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <p style="background-color:Green; color:Black">This command sends you a list of all commands</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Help implements ICommands {

    @Override
    public void doTask(MessageReceivedEvent event) throws IndexOutOfBoundsException, IOException {
        try {
            EmbedBuilder embedBuilder = new EmbedBuilder();


            embedBuilder.clear();
            int maxEmbedSize = 25;

            int counter = 0;

            int cmdSize = CommandManager.getAllCommands().size();
            embedBuilder.clear();
            if (cmdSize > maxEmbedSize) {

                for (int i = 0; i < cmdSize; i++) {
                    // for (int j = 0; j < Math.abs(i * cmdSize - (Math.abs(i * cmdSize -
                    // maxEmbedSize))); j++) {
                    for (int w = (i) * 25; w < (i * maxEmbedSize) + Math.abs(i * cmdSize - (Math.abs(i * cmdSize - maxEmbedSize))); w++) {
                        StringBuilder allTriggers = new StringBuilder();

                        if (w < cmdSize) {

                            for (String trigger : CommandManager.getCommandbyId(w).getTriggers()) {
                                allTriggers.append(Prefix.getValue()).append(trigger).append("   ");
                            }

                            embedBuilder.addField("**" + CommandManager.getCommandbyId(w).getName() + "**  :  ",
                                    CommandManager.getCommandbyId(w).whatDoIDo() + "\n" + allTriggers, true);
                            counter++;
                        } else {
                            embedBuilder.setColor(LibraryClass.getRandomColor());
                            // embedBuilder.setTitle("All " + CommandManager.getAllCommands().size() + "
                            // commands:");
                            embedBuilder.setTitle("Showing " + counter + " commands");
                            //counter = 0;
                            event.getMessage().replyEmbeds(embedBuilder.build()).queue();
                            embedBuilder.clear();

                            // counter++;
                            return;
                        }
                    }
                    // }
                    embedBuilder.setColor(LibraryClass.getRandomColor());
                    // embedBuilder.setTitle("All " + CommandManager.getAllCommands().size() + "
                    // commands:");
                    embedBuilder.setTitle("Showing " + counter + " commands");
                    counter = 0;
                    event.getMessage().replyEmbeds(embedBuilder.build()).queue();
                    embedBuilder.clear();
                }
            } else {

                for (ICommands iCommands : CommandManager.getAllCommands()) {
                    StringBuilder allTriggers = new StringBuilder();
                    for (String trigger : iCommands.getTriggers()) {
                        allTriggers.append(Prefix.getValue()).append(trigger).append("   ");
                    }

                    embedBuilder.addField("**" + iCommands.getName() + "**  :  ", iCommands.whatDoIDo() + "\n" + allTriggers, true);

                }
                embedBuilder.setColor(LibraryClass.getRandomColor());
                embedBuilder.setTitle("All " + CommandManager.getAllCommands().size() + " commands:");
                event.getMessage().replyEmbeds(embedBuilder.build()).queue();
                embedBuilder.clear();
            }

        } catch (

                Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public String getName() {

        return "Help";
    }

    @Override
    public String whatDoIDo() {

        return "This command sends you a list of all commands";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("help");

        return t;
    }

}

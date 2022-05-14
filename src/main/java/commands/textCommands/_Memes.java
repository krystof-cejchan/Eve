package commands.textCommands;

import _library_class.LibraryClass;
import commands.ICommands;
import external_files.memes__php.GetFunctionsFrom_PHP;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Objects;

/**
 * <p style="background-color:Green; color:Black">This command sends a auto-generated meme with you</p>
 *
 * @author krystof-cejchan
 * {@link ICommands}
 */
public class _Memes implements ICommands {

    /**
     * This method takes a photo of the mentioned user from the message →generates
     * random number from 1 to count of functions from PHP script →runs a php script
     * where the photo of the mentioned user is placed on a meme template →sends the
     * link to the image to a message channel
     *
     * @param event MessageReceivedEvent so that we can work with current guild,
     *              members...
     * @author krystof-cejchan
     * @since 26.02.2022
     */
    @Override
    public void doTask(MessageReceivedEvent event) throws Exception {
        String profpic_1 = event.getAuthor().getAvatarUrl();
        // example →
        // http://eveuwu.g6.cz/memes/IMemes.php?profpic1=https://i.kym-cdn.com/entries/icons/mobile/000/037/458/coverpal.jpg&rnd=1
        String website = "http://eveuwu.g6.cz/memes/IMemes.php";
        String _GET_profpic1 = "?&profpic1=";
        int max = Integer
                .parseInt(Objects.requireNonNull(GetFunctionsFrom_PHP.getCount("http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount")));
        String _GET_rnd = "&rnd=" + LibraryClass.generateRandomInt(1, max);
        String distinguisher = "&" + event.getMessage().getId();

        try {

            ArrayList<Member> member = new ArrayList<>(event.getMessage().getMentionedMembers());
            final User mentionedUser = member.get(0).getUser();

            profpic_1 = mentionedUser.getAvatarUrl();
        } catch (IndexOutOfBoundsException ignored) {
        }

        // random meme
        System.out.println(profpic_1 + " " + _GET_rnd);
        if (profpic_1 != null)
            event.getChannel().sendMessage(website + _GET_profpic1 + profpic_1 + _GET_rnd + distinguisher).queue();

    }

    /*
     * private Member getMentioned(MessageReceivedEvent event) { ArrayList<Member>
     * members = new ArrayList<>(event.getGuild().getMembers());
     *
     * for (Member member : members) { if
     * (event.getMessage().getContentRaw().contains("@" + member.getNickname())) {
     * return member; }
     *
     * }
     *
     * return event.getMessage().getMember();
     *
     * }
     */

    @Override
    public String getName() {

        return "Memes";
    }

    @Override
    public String whatDoIDo() {

        return "This command sends a auto-generated meme with you or the person you tag in your message";
    }

    @Override
    public ArrayList<String> getTriggers() {

        ArrayList<String> t = new ArrayList<>();
        t.add("meme");
        t.add("memes");
        t.add("mem");

        return t;
    }


}

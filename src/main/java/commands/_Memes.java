package commands;

import java.util.ArrayList;

import _library_class.LibraryClass;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class _Memes implements ICommands {

	@Override
	public void doTask(MessageReceivedEvent event) throws Exception {
		String profpic_1 = event.getAuthor().getAvatarUrl();
		// example →
		// http://eveuwu.g6.cz/memes/IMemes.php?profpic1=https://i.kym-cdn.com/entries/icons/mobile/000/037/458/coverpal.jpg&rnd=1
		String website = "http://eveuwu.g6.cz/memes/IMemes.php";
		String _GET_profpic1 = "?&profpic1=";
		String _GET_rnd = "&rnd=" + String.valueOf(LibraryClass.generateRandomInt(1, 2));
		String distinguisher = "&" + event.getMessage().getId();

		try {

			ArrayList<Member> member = new ArrayList<>(event.getMessage().getMentionedMembers());
			final User mentionedUser = member.get(0).getUser();

			profpic_1 = mentionedUser.getAvatarUrl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
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
		// TODO Auto-generated method stub
		return "Memes";
	}

	@Override
	public String whatDoIDo() {
		// TODO Auto-generated method stub
		return "This command sends a auto-generated meme with youuu";
	}

	@Override
	public ArrayList<String> getTriggers() {
		// TODO Auto-generated method stub
		ArrayList<String> t = new ArrayList<>();
		t.add("meme");
		t.add("memes");
		t.add("mem");

		return t;
	}

}

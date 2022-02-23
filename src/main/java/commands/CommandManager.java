package commands;

import java.util.ArrayList;
import javax.annotation.Nullable;

import main.Prefix;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CommandManager {
	public final static ArrayList<ICommands> commands = new ArrayList<>();

	public CommandManager() {
		addNewCommand(new _Help());
		addNewCommand(new _Prefix());
		addNewCommand(new _EmotionalDamage());
		addNewCommand(new _Jesus());
		addNewCommand(new _Gif());
		addNewCommand(new _Pp());
		addNewCommand(new _SaySomethingNice());
		addNewCommand(new _ServerInfo());
		addNewCommand(new _Support());
		addNewCommand(new _Me());
		addNewCommand(new _Join());
		addNewCommand(new _Leave());
		addNewCommand(new _Play());
		addNewCommand(new _PlayQueue());
		addNewCommand(new _Queue());
		addNewCommand(new _Skip1());
		addNewCommand(new _SkipTo());
		addNewCommand(new _FF());
		addNewCommand(new _Remove());
		addNewCommand(new _NowPlaying());
		addNewCommand(new _Shuffle());
		addNewCommand(new _Pause());
		addNewCommand(new _Resume());
		addNewCommand(new _Stop());
		addNewCommand(new _Volume());
		addNewCommand(new _Mute());
		addNewCommand(new _Unmute());
		addNewCommand(new _ECHO());
		addNewCommand(new _SupportedLanguages());

	}

	private void addNewCommand(ICommands Icmd) {

		boolean exists = commands.stream().anyMatch((it) -> it.getName().equals(Icmd.getName()));

		if (!exists) {
			commands.add(Icmd);
		}

	}

	public static ArrayList<ICommands> getAllCommands() {
		return commands;
	}

	@Nullable
	public ICommands getCommand(MessageReceivedEvent event) {

		try {
			String[] args = event.getMessage().getContentRaw().split(" ");
			Prefix pref = new Prefix();
			if (args[0].startsWith(pref.getValue())) {

				for (ICommands iCommands : commands) {

					for (int i = 0; i < iCommands.getTriggers().size(); i++) {

						for (int j = 0; j < commands.size(); j++) {

							if (iCommands.getTriggers().get(i).equals(args[0].substring(1).toLowerCase())) {

								return iCommands;
							}
						}
					}
				}
			}
			return null;

		} catch (

		Exception e) {
			System.out.println(e);
			return null;
		}

	}
}

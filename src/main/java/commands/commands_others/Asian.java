package commands.commands_others;

import _library_class.LibraryClass;

public class Asian {

	public static String sendEmotionalDamage() {
		String[] links = { "https://tenor.com/bLzfU.gif", "https://tenor.com/bKsTq.gif", "https://tenor.com/bKtZ4.gif",
				"https://tenor.com/bOgeZ.gif", "https://tenor.com/bOco4.gif", "https://tenor.com/bM4pO.gif" };
		return links[LibraryClass.generateRandomInt(0, links.length)];
	}

	public static String sendJesus() {

		String[] links = { "https://tenor.com/bDxn5.gif", "https://tenor.com/bGgnd.gif", "https://tenor.com/bNFnu.gif",
				"https://tenor.com/bOqal.gif", "https://tenor.com/bO3s3.gif", "https://tenor.com/bOQ0f.gif" };

		return links[LibraryClass.generateRandomInt(0, links.length)];
	}

}

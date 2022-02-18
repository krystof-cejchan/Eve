package Main;

import java.util.ArrayList;

import LIBRARYclass.LibraryClass;

public class Asian {

	GifSender gs = new GifSender();

	public String sendEmotionalDamage() {
		ArrayList<String> pole = new ArrayList<>();
		pole.add("https://tenor.com/bLzfU.gif");
		pole.add("https://tenor.com/bKsTq.gif");
		pole.add("https://tenor.com/bKtZ4.gif");
		pole.add("https://tenor.com/bOgeZ.gif");
		pole.add("https://tenor.com/bOco4.gif");
		pole.add("https://tenor.com/bM4pO.gif");
		int i = LibraryClass.generateRandomInt(0, pole.size());
		return (pole.get(i).toString());
	}

	public String sendJesus() {
		ArrayList<String> pole = new ArrayList<>();
		pole.add("https://tenor.com/bDxn5.gif");
		pole.add("https://tenor.com/bGgnd.gif");
		pole.add("https://tenor.com/bNFnu.gif");
		pole.add("https://tenor.com/bOqal.gif");
		pole.add("https://tenor.com/bO3s3.gif");
		pole.add("https://tenor.com/bOQ0f.gif");
		int i = LibraryClass.generateRandomInt(0, pole.size());
		return (pole.get(i).toString());
	}

}

package memes__php;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import _library_class.LibraryClass;

public class GetFunctionsFrom_PHP {

	public static String getCount(String weburl) {
		// http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount

		try {
			if (!LibraryClass.isLink(weburl))
				weburl = "http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount";
			URL url = new URL(weburl);
			Scanner sc = new Scanner(url.openStream());
			StringBuffer sb = new StringBuffer();
			while (sc.hasNext()) {
				sb.append(sc.next());
			}
			String result = sb.toString();
			System.out.println(result);
			sc.close();
			return result.replaceAll("<[^>]*>", "");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}

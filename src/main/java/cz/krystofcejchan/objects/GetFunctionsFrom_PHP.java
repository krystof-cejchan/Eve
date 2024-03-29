package cz.krystofcejchan.objects;

import cz.krystofcejchan.utility_class.UtilityClass;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class GetFunctionsFrom_PHP {

    public static String getCount(String weburl) {
        // http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount

        try {
            if (!UtilityClass.isLink(weburl))
                weburl = "http://eveuwu.g6.cz/memes/IMemes.php?&getFunctionCount";
            URL url = new URL(weburl);
            Scanner sc = new Scanner(url.openStream());
            StringBuilder sb = new StringBuilder();
            while (sc.hasNext()) {
                sb.append(sc.next());
            }
            String result = sb.toString();
            sc.close();
            return result.replaceAll("<[^>]*>", "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}

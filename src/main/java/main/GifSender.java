package main;

import _library_class.LibraryClass;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GifSender {

    public String call_me(String searchKey, String outputLimit) throws Exception {

        String url = "https://g.tenor.com/v1/search?q=" + searchKey + "&key=VOGDG7F3VEIJ&limit=" + outputLimit
                + "&media_filter=minimal&locale=cs&contentfilter=off";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("resp " + response);

        JSONObject myResponse = new JSONObject(response.toString());

        JSONArray results = new JSONArray(myResponse.getJSONArray("results"));

        int resultMax = results.length();

        int index = LibraryClass.generateRandomInt(0, resultMax);

        System.out.println(resultMax);

        ArrayList<JSONObject> listdata = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            listdata.add(results.optJSONObject(i));
        }
        for (JSONObject jsonObject : listdata) {
            System.out.println(jsonObject.toString());
        }
        String finalString = listdata.get(index).toString();
        String catchWord = ",\"url\":\"";
        String stopWord = "\",\"content_description\"";
        String extractedExtract = "";
        try {
            int start = finalString.lastIndexOf(catchWord);
            int end = finalString.indexOf(stopWord);
            String extract = finalString.substring(start, end);
            extractedExtract = extract.substring(8);
            System.out.print(extractedExtract);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return extractedExtract;

    }

}

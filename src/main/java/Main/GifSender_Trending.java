package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class GifSender_Trending {
	public String call_me(String outputLimit) throws Exception {

		GifSender gs = new GifSender();

		String url = "https://g.tenor.com/v1/trending_terms?key=VOGDG7F3VEIJ&limit=" + outputLimit;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");
		// add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print in String
		System.out.println("resp " + response.toString());

		// Read JSON response and print
		JSONObject myResponse = new JSONObject(response.toString());
		// JSONObject objects = new JSONObject(myResponse.getJSONObject("object"));
		JSONArray results = new JSONArray(myResponse.getJSONArray("results"));
		// JSONObject indexes = new JSONObject(results.getJSONObject(index));

		int resultMax = results.length();

		int index = gs.generateRandomInt(0, resultMax);

		System.out.println(resultMax);

		// List list = new List();
		ArrayList<JSONObject> listdata = new ArrayList<JSONObject>();
		if (results != null) {
			for (int i = 0; i < results.length(); i++) {
				listdata.add(results.optJSONObject(i));
			}
		}
		for (JSONObject jsonObject : listdata) {
			System.out.println(jsonObject.toString());
		}
		String finalString = listdata.get(index).toString();
		String catchWord = ",\"url\":\"";
		String stopWord = "\",\"content_description\"";
		String vytazekVytazku = "";
		try {
			int start = finalString.lastIndexOf(catchWord);
			int end = finalString.indexOf(stopWord);
			String vytazek = finalString.substring(start, end);
			vytazekVytazku = vytazek.substring(8);
			System.out.print(vytazekVytazku);

		} catch (Exception e) {
			System.out.println(e);
		}

		return vytazekVytazku;

	}
}

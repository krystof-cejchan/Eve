package cz.krystofcejchan.commands.api.chuckjokes;

import org.json.JSONObject;

public class ExtractedJokeFromJSONResult {
    public static String getJokeFromJSON() {
        JSONObject jsonObject = new JSONObject(JokesGetFromAPI.Companion.getJSONResult());
        return jsonObject.getString("value");
    }
}

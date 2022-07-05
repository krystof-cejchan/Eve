package commands.api.quotes_core;

import org.json.JSONException;
import org.json.JSONObject;

public class QuoteObject extends QuotesGetFromAPI {

    private final String content;
    private String author;

    public QuoteObject(String lang) {
        JSONObject jsonObject = new JSONObject(Companion.getQuote(lang));
        this.content = jsonObject.getString("content");
        try {
            this.author = jsonObject.getJSONObject("originator").getString("name");
        } catch (JSONException e) {
            this.author = "n / a";
        }
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

}

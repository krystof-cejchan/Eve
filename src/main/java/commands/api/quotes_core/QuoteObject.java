package commands.api.quotes_core;

import org.json.JSONObject;

public class QuoteObject extends QuotesGetFromAPI {

    private final String content;
    private final String author;

    public QuoteObject(String lang) {
        JSONObject jsonObject = new JSONObject(Companion.getQuote(lang));
        this.content = jsonObject.getString("content");
        this.author = jsonObject.getJSONObject("originator").getString("name");
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

}

package commands.commands_slash.quotes_core;

import org.json.JSONObject;

public class QuoteObject extends Quotes {

    private final String content;
    private final String author;

    public QuoteObject(String lang) {
        JSONObject jsonObject = new JSONObject(Quotes.Companion.getQuote(lang));
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

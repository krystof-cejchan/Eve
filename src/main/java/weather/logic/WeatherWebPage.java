package weather.logic;

import enums_annotations_exceptions.exceptions.InvalidWebAddress;

import static library_class.LibraryClass.isLink;

public class WeatherWebPage {
    private String url;

    public WeatherWebPage(String url) throws InvalidWebAddress {
        this.url = isLink(url) ? url : null;

        if (this.url == null)
            throw new InvalidWebAddress();
    }

    public void setUrl(String url) {
        if (isLink(url))
            this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}

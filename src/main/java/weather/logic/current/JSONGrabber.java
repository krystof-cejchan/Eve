package weather.logic.current;

import enums_annotations_exceptions.exceptions.InvalidWebAddress;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import weather.logic.WeatherWebPage;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JSONGrabber extends WeatherWebPage {
    public JSONGrabber(String url) throws InvalidWebAddress {
        super(url);
    }

    public WeatherCurrent_Obj getWeatherForeCast() throws IOException {
        JSONObject json = new JSONObject(IOUtils.toString(new URL(super.getUrl()), StandardCharsets.UTF_8));
        JSONArray current_condition = json.getJSONArray("current_condition");
        JSONObject zero = current_condition.getJSONObject(0);
        System.out.println(super.getUrl());

        return new WeatherCurrent_Obj(zero.getString("temp_C"), zero.getString("temp_F"), zero.getString("FeelsLikeC"),
                zero.getString("FeelsLikeF"), current_condition.getJSONObject(0).getJSONArray("weatherDesc")
                .getJSONObject(0).getString("value")
                , json.getJSONArray("nearest_area").getJSONObject(0).getJSONArray("areaName")
                .getJSONObject(0).getString("value")+", "
                +json.getJSONArray("nearest_area").getJSONObject(0).getJSONArray("country")
                .getJSONObject(0).getString("value"));


    }
}
package weather.logic.forecast.days;

import enums_annotations_exceptions.exceptions.InvalidWebAddress;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import weather.logic.WeatherWebPage;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DayInWeatherForeCast extends WeatherWebPage {
    String day;
    String time;

    String location;
    String c;
    String f;
    String feelsC;
    String feelsF;
    String windSpeedKmph;
    String windSpeedMph;
    String chanceofRain;
    String chanceofSnow;
    String chanceofSunshine;
    String chanceofThunder;
    String visibilityKm;
    String visibilityMiles;
    String weatherValue;
    String maxC, maxF, minC, minF;
    String uvIndex;
    String sunHour;
    String sunRise;
    String sunSet;

    /**
     * @param url    json url
     * @param n_day  day from 0 to 2
     * @param n_time time from 0 to 7
     *                              <div class="tg-wrap"><table>
     *               <tbody>
     *                 <tr>
     *                   <td>0</td>
     *                   <td>|0</td>
     *                 </tr>
     *                 <tr>
     *                   <td>1</td>
     *                   <td>|3</td>
     *                 </tr>
     *                 <tr>
     *                   <td>2</td>
     *                   <td>|6</td>
     *                 </tr>
     *                 <tr>
     *                   <td>3</td>
     *                   <td>|9</td>
     *                 </tr>
     *                 <tr>
     *                   <td>4</td>
     *                   <td>|12</td>
     *                 </tr>
     *                 <tr>
     *                   <td>5</td>
     *                   <td>|15</td>
     *                 </tr>
     *                 <tr>
     *                   <td>6</td>
     *                   <td>|18</td>
     *                 </tr>
     *                 <tr>
     *                   <td>7</td>
     *                   <td>|21</td>
     *                 </tr>
     *               </tbody>
     *               </table></div>
     * @throws InvalidWebAddress invalid web url{@link InvalidWebAddress}
     * @throws IOException       ?  {@link IOException}
     */
    public DayInWeatherForeCast(String url, int n_day, int n_time) throws InvalidWebAddress, IOException {
        super(url);
        JSONObject json = new JSONObject(IOUtils.toString(new URL(super.getUrl()), StandardCharsets.UTF_8));
        JSONArray current_condition = json.getJSONArray("weather");
        JSONObject day012 = current_condition.getJSONObject(n_day);
        JSONArray hourly = day012.getJSONArray("hourly");
        JSONObject certHour = hourly.getJSONObject(n_time);
        JSONObject astronomy0 = day012.getJSONArray("astronomy").getJSONObject(0);
        this.day = day012.getString("date");
        this.time = certHour.getString("time");
        this.c = certHour.getString("tempC");
        this.f = certHour.getString("tempF");
        this.feelsC = certHour.getString("FeelsLikeC");
        this.feelsF = certHour.getString("FeelsLikeF");
        this.windSpeedKmph = certHour.getString("WindGustKmph");
        this.windSpeedMph = certHour.getString("WindGustMiles");
        this.chanceofRain = certHour.getString("chanceofrain");
        this.chanceofSnow = certHour.getString("chanceofsnow");
        this.chanceofSunshine = certHour.getString("chanceofsunshine");
        this.chanceofThunder = certHour.getString("chanceofthunder");
        this.visibilityKm = certHour.getString("visibility");
        this.visibilityMiles = certHour.getString("visibilityMiles");
        this.weatherValue = certHour.getJSONArray("weatherDesc").getJSONObject(0).getString("value");
        this.location = json.getJSONArray("nearest_area").getJSONObject(0).getJSONArray("areaName")
                .getJSONObject(0).getString("value") + ", "
                + json.getJSONArray("nearest_area").getJSONObject(0).getJSONArray("country")
                .getJSONObject(0).getString("value");
        this.maxC = day012.getString("maxtempC");
        this.maxF = day012.getString("maxtempF");
        this.minC = day012.getString("mintempC");
        this.minF = day012.getString("mintempF");
        this.uvIndex = day012.getString("uvIndex");
        this.sunHour = day012.getString("sunHour");
        this.sunRise = astronomy0.getString("sunrise");
        this.sunSet = astronomy0.getString("sunset");
    }


    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getC() {
        return c;
    }

    public String getF() {
        return f;
    }

    public String getFeelsC() {
        return feelsC;
    }

    public String getFeelsF() {
        return feelsF;
    }

    public String getWindSpeedKmph() {
        return windSpeedKmph;
    }

    public String getWindSpeedMph() {
        return windSpeedMph;
    }

    public String getChanceofRain() {
        return chanceofRain;
    }

    public String getChanceofSnow() {
        return chanceofSnow;
    }

    public String getChanceofSunshine() {
        return chanceofSunshine;
    }

    public String getChanceofThunder() {
        return chanceofThunder;
    }

    public String getVisibilityKm() {
        return visibilityKm;
    }

    public String getVisibilityMiles() {
        return visibilityMiles;
    }

    public String getWeatherValue() {
        return weatherValue;
    }

    public String getLocation() {
        return location;
    }

    public String getMaxC() {
        return maxC;
    }

    public String getMaxF() {
        return maxF;
    }

    public String getMinC() {
        return minC;
    }

    public String getMinF() {
        return minF;
    }

    public String getUvIndex() {
        return uvIndex;
    }

    public String getSunHour() {
        return sunHour;
    }

    public String getSunRise() {
        return sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }
}

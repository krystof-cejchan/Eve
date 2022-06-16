package weather.logic;

import org.jetbrains.annotations.NotNull;

public class WeatherCurrent_Obj {
    final private String curr_tempC;
    final private String curr_tempF;
    final private String curr_feels_tempC;
    final private String curr_feels_tempF;
    final private String weather_value;
    final private @NotNull String location;

    public WeatherCurrent_Obj(String curr_tempC, String curr_tempF, String curr_feels_tempC, String curr_feels_tempF,
                              String weather_value, @NotNull String location) {
        this.curr_tempC = curr_tempC;
        this.curr_tempF = curr_tempF;
        this.curr_feels_tempC = curr_feels_tempC;
        this.curr_feels_tempF = curr_feels_tempF;
        this.weather_value = weather_value;
        this.location = location;
    }


    public String getCurr_tempC() {
        return curr_tempC;
    }


    public String getCurr_tempF() {
        return curr_tempF;
    }


    public String getCurr_feels_tempC() {
        return curr_feels_tempC;
    }


    public String getCurr_feels_tempF() {
        return curr_feels_tempF;
    }


    public String getWeather_value() {
        return weather_value;
    }


    public @NotNull String getLocation() {
        return location;
    }


}

package weather.logic;

public class WeatherCurrent_Obj {
    private String curr_tempC;
    private String curr_tempF;
    private String curr_feels_tempC;
    private String curr_feels_tempF;
    private String weather_value;
    private String location;

    public WeatherCurrent_Obj(String curr_tempC, String curr_tempF, String curr_feels_tempC, String curr_feels_tempF,
                              String weather_value, String location) {
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

    public void setCurr_tempC(String curr_tempC) {
        this.curr_tempC = curr_tempC;
    }

    public String getCurr_tempF() {
        return curr_tempF;
    }

    public void setCurr_tempF(String curr_tempF) {
        this.curr_tempF = curr_tempF;
    }

    public String getCurr_feels_tempC() {
        return curr_feels_tempC;
    }

    public void setCurr_feels_tempC(String curr_feels_tempC) {
        this.curr_feels_tempC = curr_feels_tempC;
    }

    public String getCurr_feels_tempF() {
        return curr_feels_tempF;
    }

    public void setCurr_feels_tempF(String curr_feels_tempF) {
        this.curr_feels_tempF = curr_feels_tempF;
    }

    public String getWeather_value() {
        return weather_value;
    }

    public void setWeather_value(String weather_value) {
        this.weather_value = weather_value;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}

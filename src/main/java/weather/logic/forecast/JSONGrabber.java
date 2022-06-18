package weather.logic.forecast;

import enums_annotations_exceptions.exceptions.InvalidWebAddress;
import weather.logic.WeatherWebPage;
import weather.logic.forecast.days.DayInWeatherForeCast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONGrabber extends WeatherWebPage {
    public JSONGrabber(String url) throws InvalidWebAddress {
        super(url);
    }

    public List<DayInWeatherForeCast> getWeatherForecastsForAllDaysAndTimes(int forskipTimes) throws IOException, InvalidWebAddress {
        ArrayList<DayInWeatherForeCast> arr = new ArrayList<>();
        for (int day = 0; day <= 2; day++) {
            for (int time = 0; time <= 7; time += forskipTimes == 0 ? 1 : forskipTimes) {
                arr.add(new DayInWeatherForeCast(super.getUrl(), day, time));
            }

        }
        return arr;
    }


}

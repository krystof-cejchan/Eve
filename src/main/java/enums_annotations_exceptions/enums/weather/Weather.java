package enums_annotations_exceptions.enums.weather;

import net.ricecode.similarity.JaroWinklerStrategy;
import net.ricecode.similarity.SimilarityStrategy;
import net.ricecode.similarity.StringSimilarityService;
import net.ricecode.similarity.StringSimilarityServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static library_class.LibraryClass.averageOfDoubleArray;

public enum Weather {
    UNKNOWN, CLOUDY, FOG, HEAVYRAIN, HEAVYSHOWERS, HEAVYSNOW, HEAVYSNOWSHOWERS, LIGHTRAIN, LIGHTSHOWERS, LIGHTSLEET,
    LIGHTSLEETSHOWERS, LIGHTSNOW, LIGHTSNOWSHOWERS, PARTLYCLOUDY, SUNNY, THUNDERYHEAVYRAIN, THUNDERYSHOWERS,
    THUNDERYSNOWSHOWERS, VERYCLOUDY;


    /**
     * "Unknown":                   "✨",
     * "Cloudy":              "☁️",
     * "Fog":                 "🌫",
     * "HeavyRain":           "🌧",
     * "HeavyShowers":        "🌧",
     * "HeavySnow":           "❄️",
     * "HeavySnowShowers":    "❄️",
     * "LightRain":           "🌦",
     * "LightShowers":        "🌦",
     * "LightSleet":          "🌧",
     * "LightSleetShowers":   "🌧",
     * "LightSnow":           "🌨",
     * "LightSnowShowers":    "🌨",
     * "PartlyCloudy":        "⛅️",
     * "Sunny":               "☀️",
     * "ThunderyHeavyRain":   "🌩",
     * "ThunderyShowers":     "⛈",
     * "ThunderySnowShowers": "⛈",
     * "VeryCloudy": "☁️",
     */
    public String getEmoji(Weather w) {
        return switch (w) {
            case UNKNOWN -> "✨";
            case CLOUDY -> "☁️";
            case FOG -> "\uD83C\uDF2B";
            case HEAVYRAIN -> "\uD83C\uDF27";
            default -> throw new IllegalStateException("Unexpected value: " + w);
        };
    }


    public static Weather getMostSuitableWeather(String input) {

        if (input == null || input.isEmpty() || input.isBlank()) return null;

        /*ArrayList<String> words = new ArrayList<>();
        new ArrayList<Weather>(List.of(Weather.values())).forEach(weather -> words.add(weather.toString()));*/
        ArrayList<Double> tempResults = new ArrayList<>();
        HashMap<Weather, Double> map = new HashMap<>();
        SimilarityStrategy strategy = new JaroWinklerStrategy();
        StringSimilarityService service = new StringSimilarityServiceImpl(strategy);
        for (Weather weather : Weather.values()) {
            try {
                double tempResult_FORLOOP = 0;

                for (int i = 0; i < Weather.values().length; i++) {
                    tempResult_FORLOOP += service.score(Weather.values()[i].toString(), input);
                }
                tempResults.add(tempResult_FORLOOP / Weather.values().length);
                map.put(weather, averageOfDoubleArray(tempResults));


            } catch (Exception e) {
                return null;
            }
        }
        Weather[] high = {null};
        Double highD = .0;
        new ArrayList<Weather>(List.of(Weather.values())).forEach(weather -> {
            System.out.println(map.get(weather)+" "+weather);
            if (map.get(weather) > .45 && map.get(weather) > highD)
                high[0] = weather;

        });
        return high[0];

    }
}

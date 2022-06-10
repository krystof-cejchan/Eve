package enums_annotations_exceptions.enums;

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
}

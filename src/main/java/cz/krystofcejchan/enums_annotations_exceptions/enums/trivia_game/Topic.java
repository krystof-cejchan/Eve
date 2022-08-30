package cz.krystofcejchan.enums_annotations_exceptions.enums.trivia_game;

public enum Topic {
    ART_LITERATURE, FILM_TV, FOOD_DRINK, GENERAL_KNOWLEDGE, GEOGRAPHY, HISTORY,
    MUSIC, SCIENCE, SOCIETY_CULTURE, SPORT_LEISURE;

    public static String getLinkParamFromTopic(Topic topic) {
        return switch (topic) {
            case ART_LITERATURE -> "arts_and_literature";
            case FILM_TV -> "film_and_tv";
            case MUSIC -> "music";
            case HISTORY -> "history";
            case SCIENCE -> "science";
            case GEOGRAPHY -> "geography";
            case FOOD_DRINK -> "food_and_drink";
            case SPORT_LEISURE -> "sport_and_leisure";
            case SOCIETY_CULTURE -> "society_and_culture";
            case GENERAL_KNOWLEDGE -> "general_knowledge";
        };
    }
}

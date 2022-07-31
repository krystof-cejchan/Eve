package cz.krystofcejchan.enums_annotations_exceptions.enums;

public enum SlashCommandCategory {
    MUSIC, ENTERTAINMENT, WEATHER, AUDIO_COMMANDS, OTHER, GUILDMANAGEMENT, PLAYLISTS;

    public static String getCategoryInFullName(SlashCommandCategory category) {
        return switch (category) {
            case MUSIC -> "Music";
            case ENTERTAINMENT -> "Entertainment / Jokes";
            case WEATHER -> "Weather";
            case AUDIO_COMMANDS -> "Audio Commands";
            case GUILDMANAGEMENT -> "Channel or Guild Management";
            case OTHER -> "Other";
            case PLAYLISTS -> "Users' Playlists";
        };
    }
}

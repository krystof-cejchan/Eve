package utility_class;

import enums_annotations_exceptions.enums.OS;

public class GlobalValues {
    /**
     * [voice.voice_and_listening.SpeechToText]
     */
    public static final int MAX_VALUE = 100;
    /**
     * [voice.voice_and_listening.SpeechToText]
     */
    public static final int MAX_SEC_AUDIO_RECORDING = 8;
    /**
     * [listeners.Listener]
     */
    public static final boolean ALLOW_NOT_IN_TEST_MODE = true;
    /**
     * [audio_player.QueueCommand]
     */
    public static final int SHOWN_TRACKS_COUNT = 30;
    /**
     * {@link commands.admin.IAdmin}
     */
    public static final String ADMIN_PREFIX = "$$$";
    /**
     * {@link main.after_startup.AddingSlashCommandsToGuilds}
     */
    public static final boolean RESET_SLASH_COMMANDS = false;
    /**
     * {@link main.Main}
     */
    public static OS operatingSystem = OS.WINDOWS;

}

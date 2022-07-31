package cz.krystofcejchan.utility_class;

import cz.krystofcejchan.commands.admin.IAdmin;
import cz.krystofcejchan.enums_annotations_exceptions.enums.OS;
import cz.krystofcejchan.main.Main;
import cz.krystofcejchan.main.after_startup.AddingSlashCommandsToGuilds;

public class GlobalValues {
    /**
     * [voice.voice_and_listening.SpeechToText]
     */
    public static final int MAX_VALUE = 100;
    /**
     * [voice.voice_and_listening.SpeechToText]
     */
    public static final int MAX_AUDIO_RECORDING_DURATION = 600;
    /**
     * [listeners.Listener]
     */
    public static final boolean ALLOW_NOT_IN_TEST_MODE = true;
    /**
     * [audio_player.QueueCommand]
     */
    public static final int SHOWN_TRACKS_COUNT = 30;
    /**
     * {@link IAdmin}
     */
    public static final String ADMIN_PREFIX = "$$$";
    /**
     * {@link AddingSlashCommandsToGuilds}
     */
    public static final boolean RESET_SLASH_COMMANDS = false;
    /**
     * {@link Main}
     */
    public static OS operatingSystem = OS.WINDOWS;

    public static String DATABASE_SONG_SEPARATOR = "}%○";

}

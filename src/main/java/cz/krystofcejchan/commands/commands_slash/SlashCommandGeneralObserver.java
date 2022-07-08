package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.utility_class.SendPrivateMsgToDev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SlashCommandGeneralObserver {
    public static Map<String, Number> map_numberOfUsedTracks = new HashMap<>();

    public static void calculateAndKeepTrackOfSlashCommandUse(ISlashCommands triggeredCommand) {
        final int USES_LIMIT = 100;
        if (map_numberOfUsedTracks.containsKey(triggeredCommand.getName()))
            map_numberOfUsedTracks.replace(triggeredCommand.getName(), map_numberOfUsedTracks.get(triggeredCommand.getName())
                    .intValue() + 1);
        else
            map_numberOfUsedTracks.put(triggeredCommand.getName(), 1);
        ArrayList<String> toBeDeleted = new ArrayList<>();
        map_numberOfUsedTracks.forEach((cmd, num) -> {
            if (num.intValue() >= USES_LIMIT) {
                SendPrivateMsgToDev.sendDevMsg(null, cmd + " has reached " + USES_LIMIT + " uses", false);
                toBeDeleted.add(cmd);
            }
        });
        if (!toBeDeleted.isEmpty())
            toBeDeleted.forEach(it -> map_numberOfUsedTracks.remove(it));

    }
}

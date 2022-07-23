package cz.krystofcejchan.commands.purecommands.playsongcommand_pure;

import cz.krystofcejchan.main.Main;
import org.junit.jupiter.api.Test;

class PlayerManagerPureTest {

    @Test
    void loadAndReturnAudioTracks() {
        PlayerManagerPure.getInstance().loadAndReturnAudioTracks(Main.publicJDA.getGuildById("523281151561826315"),
                "https://youtu.be/qgHVh5jspsA").forEach(
                System.out::println
        );
    }
}
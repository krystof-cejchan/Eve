package cz.krystofcejchan.commands.commands_voice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class ListeningCommandManagerTest {

    @Test
    void getCommand() {
        Assertions.assertEquals(new PlaySongVoice().getName(),
                Objects.requireNonNull(new ListeningCommandManager().getCommand("play song")).getName());
    }
}
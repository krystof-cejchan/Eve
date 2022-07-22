package cz.krystofcejchan.commands.commands_slash.pause_resume;

import org.junit.jupiter.api.Test;

class LogicTest {

    @Test
    void pauseOrResume() {

        boolean isPaused = true;
        if (isPaused != true)
           System.out.println("1");
        isPaused=false;
            if (isPaused != true)
            System.out.println("2");
    }
}
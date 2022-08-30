package cz.krystofcejchan.commands.commands_slash.trivia_game;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class TriviaAPITest {

    @Test
    void getJsonFromData() throws IOException {
        TriviaAPI h = new TriviaAPI(null, null);
        h.getJsonFromData().getJSONArray("incorrectAnswers").forEach(obj -> System.out.println(obj.toString()));
    }
}
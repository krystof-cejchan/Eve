package cz.krystofcejchan.commands.commands_slash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class DogTest {
    @Test
    void pattern() {
        Assertions.assertTrue(Pattern.compile("(?=.*jpg)|(?=.*png)")
                .matcher("uidsaijpgsadisdarl").find());
    }
}
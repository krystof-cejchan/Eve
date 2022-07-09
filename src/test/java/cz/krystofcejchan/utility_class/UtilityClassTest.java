package cz.krystofcejchan.utility_class;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UtilityClassTest {

    @Test
    void compareTwoArrays() {
        Assertions.assertTrue(UtilityClass.compareTwoArrays(new ArrayList<>(List.of("b")),new ArrayList<>(List.of("a","b"))));
    }
}
package cz.krystofcejchan.utility_class;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class UtilityClassTest {

    @Test
    void compareTwoArrays() {
        Assertions.assertTrue(UtilityClass.compareTwoArrays(new ArrayList<>(List.of("b")), new ArrayList<>(List.of("a", "b"))));
    }

    @Test
    void getClearArrayAndReverse() {
        List<Character> input = new ArrayList<>(List.of('a', 'b', 'a', 'a', 'g'));

        List<Character> used = new ArrayList<>();

        input.forEach(letter -> {
            if (!used.contains(letter))
                used.add(letter);
        });
        String output = "";
        for (int i = used.size() - 1; i >= 0; i--) {
            output += used.get(i);
        }
        System.out.println(output);

    }

    @Test
    void isLink() {
        System.out.println(UtilityClass.isLink("youtube:"));
    }
}
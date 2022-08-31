package cz.krystofcejchan.commands.commands_slash.weather;

import org.junit.jupiter.api.Test;

import java.util.List;

class WeatherForecastForDayAndTimeTest {

    @Test
    void executeSlashCommand() {
        List<String> list1 = List.of("Banana", "Watermelon", "Carrot");
        List<String> list2 = List.of("Apple", "Plum", "Orange", "Watermelon");

        System.out.println(list1.stream().anyMatch(list2::contains));
    }
}
package cz.krystofcejchan.buttons;

import cz.krystofcejchan.commands.commands_slash.music.Earrape;
import cz.krystofcejchan.commands.commands_slash.trivia_game.TriviaGame;
import cz.krystofcejchan.commands.commands_slash.weather.WeatherEmbedCurr;

import java.util.ArrayList;

public class ButtonManager {
    private final ArrayList<IButtons> buttonClasses = new ArrayList<>();

    public ButtonManager() {
        addNewClass(new TriviaGame());
        addNewClass(new WeatherEmbedCurr());
        addNewClass(new Earrape());
    }

    private void addNewClass(IButtons iButtons) {
        if (buttonClasses.stream().noneMatch(btnClass -> btnClass.getButtonIdentifier().equals(iButtons.getButtonIdentifier()))) {
            buttonClasses.add(iButtons);
        }
    }

    public ArrayList<IButtons> getButtonClasses() {
        return buttonClasses;
    }
}

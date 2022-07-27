package cz.krystofcejchan.buttons;

import cz.krystofcejchan.buttons.seperate_buttons.PopularPlaylistButtons;

import java.util.ArrayList;
import java.util.List;

public class ButtonManager {
    private final List<IButtons> buttonList = new ArrayList<>();

    public ButtonManager() {
        addToList(new PopularPlaylistButtons());
    }

    private void addToList(IButtons newClass) {
        if (buttonList.stream().noneMatch(btn -> btn.getId().equals(newClass.getId())))
            buttonList.add(newClass);
    }

    public List<IButtons> getButtonList() {
        return buttonList;
    }
}

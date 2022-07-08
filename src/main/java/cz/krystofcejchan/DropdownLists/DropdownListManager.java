package cz.krystofcejchan.DropdownLists;

import java.util.ArrayList;

public class DropdownListManager {
    private final ArrayList<IDropdownList> dropdownLists = new ArrayList<>();

    public DropdownListManager() {
        addList(new HelpDropdownList());
    }

    private void addList(IDropdownList list) {
        if (dropdownLists.stream().noneMatch(it -> it.getIdentificator().equalsIgnoreCase(list.getIdentificator())))
            dropdownLists.add(list);
    }

    public ArrayList<IDropdownList> getDropdownLists() {
        return dropdownLists;
    }
}

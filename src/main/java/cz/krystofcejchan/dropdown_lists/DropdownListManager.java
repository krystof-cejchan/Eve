package cz.krystofcejchan.dropdown_lists;

import java.util.ArrayList;

public class DropdownListManager {
    private final ArrayList<IDropdownList> dropdownLists = new ArrayList<>();

    public DropdownListManager() {
        addList(new HelpDropdownList());
        addList(new PlayPublicPlaylistDropdownList());
        addList(new PlayUsersCustomPlaylistDropdownList());
    }

    private void addList(IDropdownList list) {
        if (dropdownLists.stream().noneMatch(it -> it.getIdentification().equalsIgnoreCase(list.getIdentification())))
            dropdownLists.add(list);
    }

    public ArrayList<IDropdownList> getDropdownLists() {
        return dropdownLists;
    }
}

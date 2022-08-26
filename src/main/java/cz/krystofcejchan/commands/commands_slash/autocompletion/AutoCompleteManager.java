package cz.krystofcejchan.commands.commands_slash.autocompletion;

import cz.krystofcejchan.commands.commands_slash.autocompletion.classes.DayAutoCompletion;
import cz.krystofcejchan.commands.commands_slash.autocompletion.classes.FunFactSafetyAutoCompletion;
import cz.krystofcejchan.commands.commands_slash.autocompletion.classes.QuotesLanguagesCompletion;
import cz.krystofcejchan.commands.commands_slash.autocompletion.classes.SongQueueAutoCompletion;

import java.util.ArrayList;

public class AutoCompleteManager {
    private final ArrayList<IAutoCompletion> autoComplete = new ArrayList<>();

    public AutoCompleteManager() {
        addNewAutoComplete(new SongQueueAutoCompletion());
        addNewAutoComplete(new DayAutoCompletion());
        addNewAutoComplete(new QuotesLanguagesCompletion());
        addNewAutoComplete(new FunFactSafetyAutoCompletion());
    }

    private void addNewAutoComplete(IAutoCompletion autoCompletion) {
        if (autoComplete.stream().noneMatch(match -> match.equals(autoCompletion)))
            autoComplete.add(autoCompletion);
    }

    public ArrayList<IAutoCompletion> getAutoComplete() {
        return autoComplete;
    }
}

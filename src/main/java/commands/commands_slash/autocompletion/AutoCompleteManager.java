package commands.commands_slash.autocompletion;

import commands.commands_slash.autocompletion.classes.DayAutoCompletion;
import commands.commands_slash.autocompletion.classes.QuotesLanguagesCompletion;
import commands.commands_slash.autocompletion.classes.SongQueueAutoCompletion;

import java.util.ArrayList;

public class AutoCompleteManager {
    private final ArrayList<IAutoCompletion> autoComplete = new ArrayList<>();

    public AutoCompleteManager() {
        addNewAutoComplete(new SongQueueAutoCompletion());
        addNewAutoComplete(new DayAutoCompletion());
        addNewAutoComplete(new QuotesLanguagesCompletion());
    }

    private void addNewAutoComplete(IAutoCompletion autoCompletion) {
        if (autoComplete.stream().noneMatch(match -> match.equals(autoCompletion)))
            autoComplete.add(autoCompletion);
    }

    public ArrayList<IAutoCompletion> getAutoComplete() {
        return autoComplete;
    }
}

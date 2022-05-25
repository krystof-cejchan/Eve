package commands.commands_slash.autocompletion;

import commands.commands_slash.autocompletion.classes.SongQueueAutoCompletion;
import commands.commands_slash.autocompletion.classes.SongSearchAutoCompletion;

import java.util.ArrayList;

public class AutoCompleteManager {
    private final ArrayList<IAutoCompletion> autoComplete = new ArrayList<>();

    public AutoCompleteManager() {
        addNewAutoComplete(new SongQueueAutoCompletion());
        addNewAutoComplete(new SongSearchAutoCompletion());
    }

    private void addNewAutoComplete(IAutoCompletion autoCompletion) {
        boolean exists = autoComplete.stream().anyMatch(match -> match.equals(autoCompletion));

        if (!exists)
            autoComplete.add(autoCompletion);

    }

    public ArrayList<IAutoCompletion> getAutoComplete() {
        return autoComplete;
    }
}

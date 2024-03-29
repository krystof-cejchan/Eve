package cz.krystofcejchan.commands.commands_slash.autocompletion;

import cz.krystofcejchan.commands.commands_slash.autocompletion.classes.*;

import java.util.ArrayList;

public class AutoCompleteManager {
    private final ArrayList<IAutoCompletion> autoComplete = new ArrayList<>();

    public AutoCompleteManager() {
        addNewAutoComplete(new SongQueueAutoCompletion());
        addNewAutoComplete(new QuotesLanguagesCompletion());
        addNewAutoComplete(new FunFactSafetyAutoCompletion());
        addNewAutoComplete(new TriviaAutoCompletion());
        addNewAutoComplete(new WeatherDaysAndTimesAutoCompletion());
    }

    private void addNewAutoComplete(IAutoCompletion autoCompletion) {
        if (autoComplete.stream().noneMatch(match -> match.equals(autoCompletion)))
            autoComplete.add(autoCompletion);
    }

    public ArrayList<IAutoCompletion> getAutoComplete() {
        return autoComplete;
    }
}

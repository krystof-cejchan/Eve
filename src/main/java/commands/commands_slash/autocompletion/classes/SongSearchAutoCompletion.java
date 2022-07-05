package commands.commands_slash.autocompletion.classes;

import commands.commands_slash.ISlashCommands;
import commands.commands_slash.PlaySongSlash;
import commands.commands_slash.autocompletion.IAutoCompletion;
import enums_annotations_exceptions.enums.ScriptFilesLocation;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import objects.ScriptFiles;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static utility_class.UtilityClass.runPyScript;

public class SongSearchAutoCompletion implements IAutoCompletion {
    @Override
    public @NotNull List<String> getStringChoices(@NotNull CommandAutoCompleteInteractionEvent event) {

       /* ExecutorService threadpool = Executors.newCachedThreadPool();
        Future<String> task = threadpool.submit(() -> runPyScript(new ScriptFiles(ScriptFilesLocation.fromLOCAL)
                .getYtSearch().getAbsolutePath(), event.getFocusedOption().getValue()));

        System.out.println(runPyScript(new ScriptFiles(ScriptFilesLocation.fromLOCAL)
                .getYtSearch().getAbsolutePath(), event.getFocusedOption().getValue()));

        while (!task.isDone()) {System.out.println("wait");}
*/

        return new ArrayList<>(List.of(runPyScript(new ScriptFiles(ScriptFilesLocation.fromLOCAL)
                .getYtSearch().getAbsolutePath(), event.getFocusedOption().getValue(), false).split("\n")));
    }

    @Override
    public @NotNull ISlashCommands representativeCommand() {
        return new PlaySongSlash();
    }
}

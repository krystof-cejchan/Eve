package cz.krystofcejchan.commands.commands_slash.autocompletion.classes;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.commands_slash.PlaySongSlash;
import cz.krystofcejchan.commands.commands_slash.autocompletion.IAutoCompletion;
import cz.krystofcejchan.external_files.py_scripts.PyPaths;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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

        return new ArrayList<>(List.of(UtilityClass.runPyScript(PyPaths.absolutePath(PyPaths.YTSEARCH),
                event.getFocusedOption().getValue(), false).split("\n")));
    }

    @Override
    public @NotNull ISlashCommands representativeCommand() {
        return new PlaySongSlash();
    }
}

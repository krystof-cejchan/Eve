package main.after_startup;

import main.Main;
import net.dv8tion.jda.api.entities.Guild;

public class DeletingAllSlashCommandsFromAllGuilds implements IAfterStartUp {
    @Override
    public void doAfterStartUp() {
        Main.publicJDA.getGuilds().forEach(Guild::updateCommands);
    }
}

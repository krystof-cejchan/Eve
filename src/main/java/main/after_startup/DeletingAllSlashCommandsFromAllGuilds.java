package main.after_startup;

import main.StartUp;
import net.dv8tion.jda.api.entities.Guild;

public class DeletingAllSlashCommandsFromAllGuilds implements IAfterStartUp {
    @Override
    public void doAfterStartUp() {
        StartUp.publicJDA.getGuilds().forEach(Guild::updateCommands);
    }
}

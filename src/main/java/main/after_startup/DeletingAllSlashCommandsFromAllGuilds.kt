package main.after_startup

import main.StartUp

class DeletingAllSlashCommandsFromAllGuilds : IAfterStartUp {
    override fun doAfterStartUp() {
        // SlashCommandManager slashCommandManager = new SlashCommandManager();
        for (guild in StartUp.publicJDA.guilds) {
            guild.updateCommands()
        }
    }
}
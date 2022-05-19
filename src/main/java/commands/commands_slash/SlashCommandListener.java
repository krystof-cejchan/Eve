package commands.commands_slash;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.CurrentTextChannel;
import org.jetbrains.annotations.NotNull;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        CurrentTextChannel.setIid(event.getChannel().getId());
        System.out.println(CurrentTextChannel.getId());

        System.out.println(event.getName());
        SlashCommandManager slashCommandManager = new SlashCommandManager();
        if (slashCommandManager.getSlashCommand(event) != null)
            slashCommandManager.getSlashCommand(event).executeSlashCommand(event);
    }
}

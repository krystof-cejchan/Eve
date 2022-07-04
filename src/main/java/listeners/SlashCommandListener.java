package listeners;

import commands.commands_slash.ISlashCommands;
import commands.commands_slash.JoinSlash;
import commands.commands_slash.SlashCommandManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import objects.CurrentTextChannel;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

import static commands.purecommands.subparts.GetUsersVoiceChannels.botsAudioChannel;
import static commands.purecommands.subparts.GetUsersVoiceChannels.usersAudioChannel;

public class SlashCommandListener extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        CurrentTextChannel.setIid(event.getChannel().getId());

        SlashCommandManager slashCommandManager = new SlashCommandManager();
        if (slashCommandManager.getSlashCommand(event) != null) {
            if (botsAudioChannel(event.getGuild()) != null
                    &&
                    !botsAudioChannel(event.getGuild()).equals(usersAudioChannel(Objects.requireNonNull(event.getMember())))) {
                event.replyEmbeds(new EmbedBuilder().setColor(Color.RED).setTitle("Error").addField(
                        "Bot's joined channel differs from yours!",
                        "Join the bot's voice channel or let the bot join you by using " + new JoinSlash()
                                .getName() + " command", false).build()).queue();
                return;
            }
            ISlashCommands triggeredCommand = slashCommandManager.getSlashCommand(event);
            triggeredCommand.executeSlashCommand(event);
         //   SlashCommandGeneralObserver.calculateAndKeepTrackOfSlashCommandUse(triggeredCommand);
        }
    }
}

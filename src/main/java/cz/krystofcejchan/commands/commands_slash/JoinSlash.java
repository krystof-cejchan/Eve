package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.enums_annotations_exceptions.annotations.Slash;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.main.VoiceChannels;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

@Slash()
public class JoinSlash implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        new VoiceChannels().joinSlash(slashEvent, true);
    }

    @Override
    public @NotNull String getDescription() {
        return "Join User's voice channel";
    }

    @Override
    public @NotNull String getName() {
        return "join_me";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.NONE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return null;
    }

    @Override
    public List<String> getArgName() {
        return null;
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return false;
    }

    @Override
    public @NotNull List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.GUILDMANAGEMENT);
    }
}

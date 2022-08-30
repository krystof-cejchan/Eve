package cz.krystofcejchan.commands.commands_slash;

import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

public class Latency implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        long time = System.currentTimeMillis();
        slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.ORANGE)
                        .setTitle("Calculating response time...")
                        .build())
                .setEphemeral(false)
                .flatMap(v ->
                        slashEvent.getHook().editOriginalEmbeds(new EmbedBuilder().setColor(UtilityClass.getRandomColor())
                                .setTitle("Latency has been calculated to " + (System.currentTimeMillis() - time) + "ms")
                                .build())
                ).queue();
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Get bot's latency / response time ";
    }

    @NotNull
    @Override
    public String getName() {
        return "latency";
    }

    @NotNull
    @Override
    public ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.NONE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        return null;
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return null;
    }

    @Override
    public boolean isGuildOnly() {
        return false;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return false;
    }

    @NotNull
    @Override
    public List<SlashCommandCategory> getCategory() {
        return List.of(SlashCommandCategory.OTHER, SlashCommandCategory.GUILDMANAGEMENT);
    }
}

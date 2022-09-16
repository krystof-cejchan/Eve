package cz.krystofcejchan.commands.commands_slash.music;

import cz.krystofcejchan.audioplayer.PlayerManager;
import cz.krystofcejchan.buttons.IButtons;
import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.purecommands.VolumePure;
import cz.krystofcejchan.commands.purecommands.subparts.GetCurrentVolume;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Earrape implements ISlashCommands, IButtons {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(slashEvent.getGuild()))
                .AUDIOPLAYER.setVolume(1000);
        slashEvent.replyEmbeds(new EmbedBuilder().setImage("https://memegenerator.net/img/images/73338515.jpg")
                        .setTitle("Volume: %s \uD83D\uDE35\u200D\uD83D\uDCAB"
                                .formatted(String.valueOf(GetCurrentVolume.getVolume(slashEvent.getGuild()))))
                        .setColor(Color.magenta).build())
                .addActionRow(Button.danger(getButtonIdentifier(), "Bring back my sanity!").asEnabled())
                .queue();
    }

    @NotNull
    @Override
    public String getDescription() {
        return "you better take those headphones off";
    }

    @NotNull
    @Override
    public String getName() {
        return "ear-rape";
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
        return true;
    }

    @NotNull
    @Override
    public List<SlashCommandCategory> getCategory() {
        return Collections.singletonList(SlashCommandCategory.MUSIC);
    }

    @NotNull
    @Override
    public String getButtonIdentifier() {
        return "earrape";
    }

    @Override
    public void handleEvent(ButtonInteractionEvent event) {
        VolumePure.setVolumeTo(event.getGuild(), 100);
        event.editMessageEmbeds(new EmbedBuilder()
                .setImage("https://static.wikia.nocookie.net/joey-slikk-alt/images/e/e8/Mr_Incredible_%28Uncanny_Phase_1%29.png" +
                        "/revision/latest/top-crop/width/360/height/360?cb=20220624004547")
                .setTitle("Volume: %s \uD83D\uDE2E\u200D\uD83D\uDCA8"
                        .formatted(String.valueOf(GetCurrentVolume.getVolume(event.getGuild()))))
                .setColor(Color.YELLOW).build())
                .setActionRow(Button.success(getButtonIdentifier(),"You are in the safe zone for now").asDisabled())
                .queue();
    }
}

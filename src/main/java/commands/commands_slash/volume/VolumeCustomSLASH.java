package commands.commands_slash.volume;

import commands.commands_slash.ISlashCommands;
import commands.purecommands.VolumePure;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import utility_class.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static commands.purecommands.subparts.GetCurrentVolume.getVolume;

public class VolumeCustomSLASH implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            int oldVol = getVolume(slashEvent.getGuild());

            VolumePure.setVolumeTo(slashEvent.getGuild(), (int) Objects.requireNonNull(slashEvent.getOption(Objects
                    .requireNonNull(Objects.requireNonNull(getArgName()).get(0)))).getAsDouble());


            slashEvent.replyEmbeds(embed.get(Objects.requireNonNull(slashEvent.getMember()), slashEvent.getGuild(), oldVol)).queue();
        } catch (Exception e) {
            e.printStackTrace();
            slashEvent.reply("There's been an error; make sure you entered valid input").queue();
        }
    }

    @Override
    public @NotNull String getDescription() {
        return "Set volume for bot's audio output";
    }

    @Override
    public @NotNull String getName() {
        return "volume";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.ONE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.NUMBER, Objects.requireNonNull(Objects.requireNonNull
                (getArgName()).get(0)),
                "enter an integer number from 0 - 200 (150 recommended)",
                true, false).setMinValue(0).setMaxValue(200)));
    }

    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("volume"));
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return true;
    }

    static class embed {
        static MessageEmbed get(Member author, Guild guild, int oldVol) {
            int newVol = getVolume(guild);
            String increasedOrDecreased = (newVol > oldVol ? "increased" : "decreased");
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(UtilityClass.getRandomColor());
            builder.setTitle("Volume controller \uD83D\uDD79");
            builder.addField("Volume before: ", String.valueOf(oldVol), true);
            builder.addField("New Volume: ", String.valueOf(newVol), true);
            builder.addField("", "Volume has " + increasedOrDecreased + " by " + Math.abs(newVol - oldVol),
                    false);
            builder.setFooter(author.getNickname() + " [" + author.getUser().getName() + "]",
                    author.getEffectiveAvatarUrl());
            return builder.build();
        }
    }
}

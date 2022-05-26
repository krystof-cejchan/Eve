package commands.commands_slash.volume;

import _library_class.LibraryClass;
import commands._pure_commands.Volume_PURE;
import commands.commands_slash.ISlashCommands;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static commands._pure_commands.subparts.GetCurrentVolume.getVolume;

public class VolumeCustomSLASH implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            int oldVol = getVolume(slashEvent.getGuild());
            Volume_PURE.setVolumeTo(slashEvent.getGuild(),
                    Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()))).getAsInt());


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
    public boolean takesArguments() {
        return true;
    }

    @Nullable
    @Override
    public OptionData getOptionData() {
        return new OptionData(OptionType.INTEGER, Objects.requireNonNull(getArgName()),
                "enter an integer number",
                true, false);
    }

    @Nullable
    @Override
    public String getArgName() {
        return "volume";
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }

    static class embed {
        static MessageEmbed get(Member author, Guild guild, int oldVol) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(LibraryClass.getRandomColor());
            builder.setTitle("Volume controller");
            builder.addField("Volume before: ", String.valueOf(oldVol), true);
            builder.addField("New Volume: ", String.valueOf(getVolume(guild)), true);
            builder.setAuthor(author.getNickname());
            return builder.build();
        }
    }
}

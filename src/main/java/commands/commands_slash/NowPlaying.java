package commands.commands_slash;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import commands.purecommands.subparts.GetCurrentTrack;
import enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import utility_class.UtilityClass;

import java.awt.*;
import java.util.List;

public class NowPlaying implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        AudioTrack npTrack = GetCurrentTrack.getTrack(slashEvent.getGuild());
        if (npTrack == null) {
            slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.RED).addField("No track is playing at the moment!",
                    "You can play a track by using /"
                            + new PlaySongSlash().getName(), false).build()).queue();
        } else {
            AudioTrackInfo info = npTrack.getInfo();
            String position = UtilityClass.getTimeStampMilliToStringTime(npTrack.getPosition());
            String duration = UtilityClass.getTimeStampMilliToStringTime(npTrack.getDuration());
            String timeLeft = UtilityClass.getTimeStampMilliToStringTime(npTrack.getDuration() - npTrack.getPosition());
            slashEvent.replyEmbeds(new EmbedBuilder().setColor(UtilityClass.getRandomColor()).setTitle("Currently-Playing Song.")
                    .addField("Title:", info.title, true).addField("Author:", info.author, true)
                    .addField("TimeLine:", position + " / " + duration + "\t[Time left: *" + timeLeft + "*]", false)
                    .addField("","[Link to the source](" + info.uri + ")",false).build()).queue();
            //[Link text](http://example.com)
        }
    }

    @Override
    public @NotNull String getDescription() {
        return "Show the currently playing song and its properties";
    }

    @Override
    public @NotNull String getName() {
        return "nowplaying";
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
        return true;
    }

    @Override
    public boolean isUserRequiredToBeInTheSameChannelAsBot() {
        return false;
    }
}

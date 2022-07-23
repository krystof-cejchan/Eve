package cz.krystofcejchan.commands.commands_slash.users_custom_playlists.open;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.commit_queries.Queries;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.connection.ConnectToDatabase;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.objects.records.PublicPlaylistsRecord;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AddPublicPlaylistToDatabaseSlashCommand implements ISlashCommands {
    private final int ARG_LIMIT = 25;

    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        int notLink = 0;
        // slashEvent.deferReply().queue();
        ArrayList<String> args = new ArrayList<>();
        args.add(0, Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0))).getAsString());
        args.add(1, Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(1))).getAsString());
        for (int i = 2; i < slashEvent.getOptions().size(); i++) {
            if (!UtilityClass.isLink(slashEvent.getOptions().get(i).getAsString()) || !slashEvent.getOptions().get(i).getAsString().contains("you")) {
                notLink++;
                continue;
            }
            args.add(i, slashEvent.getOptions().get(i).getAsString());
        }

       /* String linksToAllTracks =
                UsersInput2AudioTracks.transformUsersInputToAudioTracks(slashEvent.getGuild(), args.subList(2, args.size()))
                        .stream()
                        .map(audioTrackInfo -> audioTrackInfo.uri)
                        .collect(Collectors.joining(";"));*/

//        while (linksToAllTracks.isEmpty() || linksToAllTracks.isBlank()) {
//            try {
//                System.out.println("skee");
//                Thread.sleep(1000);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }


        PublicPlaylistsRecord record = new PublicPlaylistsRecord(args.get(0), args.get(1),
                Objects.requireNonNull(slashEvent.getGuild()).getIdLong(),
                slashEvent.getUser().getId(), String.join(";", args.subList(2, args.size())));
        try {
            Queries.addRecord(Objects.requireNonNull(ConnectToDatabase.getInstance().connectToDatabase()), record);
        } catch (SQLException sqlException) {
            slashEvent.reply("There was an error while handling your request").setEphemeral(true).queue();
        }
        slashEvent.replyEmbeds(new EmbedBuilder().setColor(Color.GREEN).setTitle("Public playlist successfully added!")
                .addField("Playlist data:", record.toString(), false)
                .addField("Stats:", "Success: " + (slashEvent.getOptions().size() - 2 - notLink) + "\nFailed: " + notLink,
                        false).build()).setEphemeral(false).queue();
    }

    @Override
    public @NotNull String getDescription() {
        return "Save your playlist so that other users can play it world-wide!";
    }

    @Override
    public @NotNull String getName() {
        return "save_playlist_public";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        List<OptionData> optionDataList = new ArrayList<>();
        optionDataList.add(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                "Title of your playlist", true, false));
        optionDataList.add(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(1),
                "short description of your playlist", true, false));
        for (int i = 2; i < ARG_LIMIT; i++) {
            optionDataList.add(new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(i),
                    i + ". song url or title", i == 2, false));
        }
        return optionDataList;
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        List<String> argList = new ArrayList<>();
        argList.add("title");
        argList.add("description");
        for (int i = 2; i < ARG_LIMIT; i++) {
            argList.add(i - 1 + "_song-url");
        }
        return argList;
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
        return Collections.singletonList(SlashCommandCategory.MUSIC);
    }
}

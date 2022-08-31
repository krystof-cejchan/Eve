package cz.krystofcejchan.commands.commands_slash.music.users_custom_playlists.open.get;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.commands_slash.music.users_custom_playlists.open.add.AddPublicPlaylistToDatabaseSlashCommand;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.commit_queries.Queries;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.connection.ConnectToDatabase;
import cz.krystofcejchan.dropdown_lists.PlayUsersCustomPlaylistDropdownList;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.GlobalValues;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetUsersPersonalPlaylists extends PlayUsersCustomPlaylistDropdownList implements ISlashCommands {
    public static List<String> songs = new ArrayList<>();
    private int generateEmbedCounter = 0;


    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        try {
            List<List<String>> databaseRes =
                    Queries
                            .Retrieve
                            .usersCreatedPlaylists(Objects.requireNonNull(ConnectToDatabase.getInstance().connectToDatabase()),
                                    slashEvent.getUser().getIdLong());

            SelectMenu.Builder selectMenu = SelectMenu.create(super.getIdentification());
            selectMenu.setPlaceholder("Select playlist(s) to be queued...");
            for (int i = 0; i < databaseRes.size(); i++)
                selectMenu.addOption("Play playlist n. " + (i + 1), String.valueOf(i + 1));

            selectMenu.setRequiredRange(1, selectMenu.getOptions().size());

            slashEvent.replyEmbeds(generateEmbed(databaseRes).build()).addActionRow(selectMenu.build()).queue();

        } catch (IllegalArgumentException e) {
            slashEvent.replyEmbeds(new EmbedBuilder()
                            .setTitle("It seems I was not able to find any data")
                            .addField("How to prevent it?",
                                    "You need to save a playlist" +
                                            "\nDo this by using this command:" +
                                            new AddPublicPlaylistToDatabaseSlashCommand().getName(), false)
                            .setColor(Color.RED)
                            .build())
                    .setEphemeral(true)
                    .queue();
        } catch (SQLException e) {
            slashEvent.replyEmbeds(new EmbedBuilder()
                            .setTitle("It seems something went wrong with the database :(")
                            .setColor(Color.RED)
                            .build())
                    .setEphemeral(true)
                    .queue();
        }
    }

    private EmbedBuilder generateEmbed(List<List<String>> listOList) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        listOList.forEach(list -> {
                    embedBuilder.addField("Playlist => " + ++generateEmbedCounter,
                            "Title: " + list.get(1) + "\nDescription: " + list.get(2) + "\nSongs: " +
                                    list.get(5).replaceAll(GlobalValues.DATABASE_SONG_SEPARATOR, ", ") +
                                    "\nCreated on **" + list.get(6).substring(0, list.get(6).indexOf("T")) + "**",
                            true);
                    songs.add(list.get(5));
                }
        );
        return embedBuilder.setColor(UtilityClass.getRandomColor()).setTitle("Your playlists");
    }

    @Override
    public @NotNull String getDescription() {
        return "get & play your own public playlists";
    }

    @Override
    public @NotNull String getName() {
        return "play_my_playlists";
    }

    @Override
    public @NotNull ArgumentSlashCommandCount takesArguments() {
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

    @Override
    public @NotNull List<SlashCommandCategory> getCategory() {
        return List.of(SlashCommandCategory.MUSIC, SlashCommandCategory.PLAYLISTS);
    }
}

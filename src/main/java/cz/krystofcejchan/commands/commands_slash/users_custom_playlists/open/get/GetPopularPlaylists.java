package cz.krystofcejchan.commands.commands_slash.users_custom_playlists.open.get;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.commands_slash.users_custom_playlists.logics.DatabaseData;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.commit_queries.Queries;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.connection.ConnectToDatabase;
import cz.krystofcejchan.dropdown_lists.PlayPublicPlaylistDropdownList;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.main.Main;
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

public class GetPopularPlaylists extends PlayPublicPlaylistDropdownList implements ISlashCommands {
    /**
     * arraylist containing {@link DatabaseData}
     */
    public static List<DatabaseData> databaseDataRecords = new ArrayList<>();

    private int generateEmbedCounter = 0;

    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        try {
            List<List<String>> databaseRes = Queries
                    .Retrieve
                    .mostPopularRecords(Objects.requireNonNull(ConnectToDatabase.getInstance().connectToDatabase()));

            SelectMenu.Builder selectMenu = SelectMenu.create(super.getIdentification());
            selectMenu.setPlaceholder("Select playlist(s) to be queued...");
            for (int i = 0; i < databaseRes.size(); i++)
                selectMenu.addOption("Play playlist n. " + (i + 1), String.valueOf(i + 1));

            selectMenu.setRequiredRange(1, selectMenu.getOptions().size());

            slashEvent.replyEmbeds(generateEmbed(databaseRes).build()).addActionRow(selectMenu.build()).queue();

        } catch (IllegalArgumentException e) {
            slashEvent.replyEmbeds(new EmbedBuilder()
                            .setTitle("It seems I was not able to find any data")
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

    @NotNull
    private EmbedBuilder generateEmbed(List<List<String>> listOList) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        listOList.forEach(list -> {
                    System.out.println(list.get(0));

                    embedBuilder.addField("Playlist => " + ++generateEmbedCounter,
                            "Title: " + list.get(1) + "\nDescription: " + list.get(2) + "\nSongs: " +
                                    list.get(5).replaceAll(GlobalValues.DATABASE_SONG_SEPARATOR, ", ") +
                                    "\nCreated by **" + Objects.requireNonNull(Main.publicJDA
                                    .getUserById(list.get(4))).getName() +
                                    "** on **" + Objects.requireNonNull(Main.publicJDA
                                    .getGuildById(list.get(3))).getName() + "** server",
                            true);
                    byte c = 0;
                    databaseDataRecords.add(new DatabaseData(list.get(c),
                            list.get(++c), list.get(++c),
                            list.get(++c),
                            list.get(++c),
                            list.get(++c),
                            list.get(++c),
                            list.get(++c),
                            list.get(++c)));
                }
        );
        return embedBuilder.setColor(UtilityClass.getRandomColor()).setTitle("The most popular play-lists");
    }

    @Override
    public @NotNull
    String getDescription() {
        return "Get the most popular playlists and choose from them!";
    }

    @Override
    public @NotNull
    String getName() {
        return "play_most_popular_playlists";
    }

    @Override
    public @NotNull
    ArgumentSlashCommandCount takesArguments() {
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

    @Override
    public @NotNull
    List<SlashCommandCategory> getCategory() {
        return List.of(SlashCommandCategory.MUSIC, SlashCommandCategory.PLAYLISTS);
    }
}

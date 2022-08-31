package cz.krystofcejchan.dropdown_lists;

import cz.krystofcejchan.audioplayer.PlayCommand;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.commit_queries.Queries;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.connection.ConnectToDatabase;
import cz.krystofcejchan.main.Main;
import cz.krystofcejchan.utility_class.GlobalValues;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cz.krystofcejchan.commands.commands_slash.music.users_custom_playlists.open.get.GetPopularPlaylists.databaseDataRecords;

public class PlayPublicPlaylistDropdownList implements IDropdownList {
    int counter = 0;

    @Override
    public void handleEvent(SelectMenuInteractionEvent event) {

        if (databaseDataRecords.isEmpty()) {
            event.reply("It seems that interaction with this message is no longer supported " +
                    "- **try triggering the command that generated this message again!**").setEphemeral(true).queue();
            return;
        }

        List<String> local_songs = new ArrayList<>();
        List<String> local_authors = new ArrayList<>();
        event.getSelectedOptions()
                .stream()
                .map(SelectOption::getValue)
                .toList()
                .forEach(input -> {
                    local_songs.add((databaseDataRecords.get(Integer.parseInt(input) - 1)).songs());
                    local_authors.add((databaseDataRecords.get(Integer.parseInt(input) - 1)).author());

                    try {
                        Queries.countUp(Objects.requireNonNull(ConnectToDatabase.getInstance().connectToDatabase()),
                                databaseDataRecords.get(Integer.parseInt(input) - 1).id(),
                                databaseDataRecords.get(Integer.parseInt(input) - 1).played_n(),
                                true);
                    } catch (SQLException e) {
                        System.out.println("played_n was not changed");
                    }
                });

        local_songs.forEach(stringOfSongs -> Arrays.stream(stringOfSongs.split(GlobalValues.DATABASE_SONG_SEPARATOR))
                .toList()
                .forEach(song -> {
                    new PlayCommand()
                            .playMusicFromDropdownList(event, song, null, false, true);

                    counter++;
                }));
        event.replyEmbeds(new EmbedBuilder()
                        .setTitle(event.getSelectedOptions().size() > 1 ? "Playing the most popular playlists"
                                : "Playing one of the most popular playlists")
                        .setDescription(counter + " song" + (counter <= 1 ? " has been sent to the queue" : "s have been sent to the queue"))
                        .addField("Authors", "Playlist" + (local_authors.size() == 1 ? "" : "s") + " from " +
                                        local_authors.stream()
                                                .map(authorsId -> Objects.requireNonNull(
                                                        Main.publicJDA.getUserById(authorsId)).getName())
                                                .collect(Collectors.joining(" *&* ")),
                                false)
                        .setColor(UtilityClass.getRandomColor()).build())
                .queue();

        //clearing arraylist
        databaseDataRecords.clear();

    }

    @NotNull
    @Override
    public String getIdentification() {
        return "public_playlist_dropdown_playlist";
    }
}

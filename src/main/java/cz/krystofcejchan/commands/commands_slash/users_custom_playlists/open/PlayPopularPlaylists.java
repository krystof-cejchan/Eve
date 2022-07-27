package cz.krystofcejchan.commands.commands_slash.users_custom_playlists.open;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.commit_queries.Queries;
import cz.krystofcejchan.database.sqlite.users_custom_playlists.connection.ConnectToDatabase;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayPopularPlaylists implements ISlashCommands {

    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        try {
            List<List<String>> databaseRes = Queries
                    .Retrieve
                    .mostPopularRecords(Objects.requireNonNull(ConnectToDatabase.getInstance().connectToDatabase()));


            List<ActionRow> buttonsAsActionRows = new ArrayList<>();
            for (int i = 0; i < databaseRes.size(); i++) {
                buttonsAsActionRows
                        .add(ActionRow
                                .of(Button
                                        .of(ButtonStyle.SECONDARY,
                                                i + "popular_public_playlist",
                                                "Play play-list n." + i)
                                )
                        );
            }


            slashEvent.replyEmbeds(generateEmbed(databaseRes).build())
                    .addActionRows(buttonsAsActionRows).queue();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EmbedBuilder generateEmbed(List<List<String>> listOList) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        listOList.forEach(list ->
                embedBuilder.addField("Info [title: " + list.get(1) + "]",
                        "Title: " + list.get(1) + "\nDescription: " + list.get(2) + "\n", true)
        );
        return embedBuilder.setColor(UtilityClass.getRandomColor()).setTitle("The most popular play-lists");
    }

    @Override
    public @NotNull String getDescription() {
        return "Get the most popular playlists and choose from them!";
    }

    @Override
    public @NotNull String getName() {
        return "play_most_popular_playlists";
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
        return false;
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

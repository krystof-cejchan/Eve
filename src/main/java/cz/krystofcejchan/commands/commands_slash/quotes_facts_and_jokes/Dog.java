package cz.krystofcejchan.commands.commands_slash.quotes_facts_and_jokes;

import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import javax.annotation.CheckForNull;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Dog implements ISlashCommands {
    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {
        slashEvent.replyEmbeds(new EmbedBuilder().setColor(UtilityClass.getRandomColor())
                        .setTitle("Your random doggo")
                        .addField("Interesting fact",
                                Objects.requireNonNull(jsonObject("https://www.dogfactsapi.ducnguyen.dev/api/v1/facts/?number=1"))
                                        .getJSONArray("facts").getString(0), false)
                        .setImage(Objects.requireNonNull(pictureUrl("https://random.dog/woof.json")))
                        .build())
                .setEphemeral(false).queue();

    }

    @CheckForNull
    private JSONObject jsonObject(@NotNull String url) {
        try {
            return new JSONObject(IOUtils.toString(new URL(url), StandardCharsets.UTF_8));
        } catch (IOException e) {
            return null;
        }
    }

    private String pictureUrl(String urlJson) {
        String url = Objects.requireNonNull(jsonObject(urlJson)).getString("url");
        while (url != null && !(Pattern.compile("(?=.*jpg)|(?=.*png)")
                .matcher(url)
                .find())) {
            url = Objects.requireNonNull(jsonObject(urlJson)).getString("url");
        }

        return url;
    }

    @NotNull
    @Override
    public String getDescription() {
        return "returns a picture of dog with a random fact about dogs";
    }

    @NotNull
    @Override
    public String getName() {
        return "dogs-are-cool";
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
        return Collections.singletonList(SlashCommandCategory.ENTERTAINMENT);
    }
}

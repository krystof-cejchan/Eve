package cz.krystofcejchan.commands.commands_slash.trivia_game;

import cz.krystofcejchan.enums_annotations_exceptions.enums.trivia_game.Difficulty;
import cz.krystofcejchan.enums_annotations_exceptions.enums.trivia_game.Topic;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static cz.krystofcejchan.enums_annotations_exceptions.enums.trivia_game.Topic.getLinkParamFromTopic;

public class TriviaAPI {
    private final JSONObject jsonObject;
    private final Topic topic;
    private final Difficulty difficulty;

    private final String question, correctAnswer;

    private final List<String> incorrectAnswers = new ArrayList<>();

    private boolean isAcceptable=true;


    public TriviaAPI(@Nullable Topic topic, @Nullable Difficulty difficulty) throws IOException {
        this.topic = topic;
        this.difficulty = difficulty;

        jsonObject = getJsonFromData();

        question = jsonObject.getString("question");
        correctAnswer = jsonObject.getString("correctAnswer");
        getJsonFromData().getJSONArray("incorrectAnswers")
                .forEach(incorrectAnswer ->
                    incorrectAnswers.add(incorrectAnswer.toString()));

        for (String incorrectAnswer : incorrectAnswers) {
            if (Button.LABEL_MAX_LENGTH < incorrectAnswer.length()) {
                isAcceptable = false;
                break;
            }
        }

    }

    EmbedBuilder generateEmbed(@NotNull Member author) {
        return new EmbedBuilder().setColor(UtilityClass.getRandomColor()).setTitle("Trivia Question")
                .setDescription("Topic: " + (this.topic == null ? "*not set*" : "**" + topic + "**")
                        + "\nDifficulty: **" + (this.difficulty == null ? "medium" : difficulty.toString()) + "**")
                .addField("The question", "```bash\n\"" + question + "\"\n```", false)
                .setAuthor(author.getNickname(), author.getEffectiveAvatarUrl(), author.getEffectiveAvatarUrl())
                .setTimestamp(ZonedDateTime.now());
    }

    @NotNull
    JSONObject getJsonFromData() throws IOException {
        return new JSONArray(
                IOUtils.toString(
                        new URL("https://the-trivia-api.com/api/questions?%slimit=1&difficulty=%s".formatted(
                                (this.topic == null ? "" : "categories=" + getLinkParamFromTopic(this.topic) + "&"),
                                (this.difficulty == null ? "medium" : this.difficulty.toString().toLowerCase(Locale.ROOT)))),
                        StandardCharsets.UTF_8)).getJSONObject(0);
    }


    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public Topic getTopic() {
        return topic;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public boolean isAcceptable() {
        return isAcceptable;
    }

    public void setAcceptable(boolean acceptable) {
        isAcceptable = acceptable;
    }
}

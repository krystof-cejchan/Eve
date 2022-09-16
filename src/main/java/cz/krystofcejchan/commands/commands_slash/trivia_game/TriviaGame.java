package cz.krystofcejchan.commands.commands_slash.trivia_game;

import cz.krystofcejchan.buttons.IButtons;
import cz.krystofcejchan.commands.commands_slash.ISlashCommands;
import cz.krystofcejchan.commands.commands_slash.SlashCommandManager;
import cz.krystofcejchan.enums_annotations_exceptions.enums.ArgumentSlashCommandCount;
import cz.krystofcejchan.enums_annotations_exceptions.enums.SlashCommandCategory;
import cz.krystofcejchan.enums_annotations_exceptions.enums.trivia_game.Difficulty;
import cz.krystofcejchan.enums_annotations_exceptions.enums.trivia_game.Topic;
import cz.krystofcejchan.utility_class.UtilityClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TriviaGame implements ISlashCommands, IButtons {
    private static Difficulty difficulty = null;
    private static Topic topic = null;

    @Override
    public void executeSlashCommand(@NotNull SlashCommandInteractionEvent slashEvent) {

        if (slashEvent.getOptions().stream().map(OptionMapping::getName)
                .toList().contains(Objects.requireNonNull(getArgName()).get(0))) {
            difficulty = Difficulty.valueOf(Objects.requireNonNull(slashEvent.getOption(getArgName().get(0))).getAsString());
        }
        if (slashEvent.getOptions().stream().map(OptionMapping::getName)
                .toList().contains(Objects.requireNonNull(getArgName()).get(1))) {
            topic = Topic.valueOf(Objects.requireNonNull(slashEvent.getOption(getArgName().get(1))).getAsString());
        }
        try {
            TriviaAPI api = new TriviaAPI(topic, difficulty);
            while (!api.isAcceptable()) {
                api = new TriviaAPI(topic, difficulty);
            }

            List<Button> btnList = new ArrayList<>();
            int rndN = UtilityClass.generateRandomInt(0, api.getIncorrectAnswers().size());
            for (int i = 0; i < api.getIncorrectAnswers().size(); i++)
                btnList.add(Button.primary(getButtonIdentifier() + "no" + i,
                        api.getIncorrectAnswers().get(i)));

            btnList.add(rndN, Button.primary(getButtonIdentifier() + "yes", api.getCorrectAnswer()));
            slashEvent.replyEmbeds(api.generateEmbed(Objects.requireNonNull(slashEvent.getMember()))
                            .build()).setEphemeral(false).addActionRow(btnList)
                    .queue();

        } catch (IOException e) {
            slashEvent.replyEmbeds(SlashCommandManager.generateErrorMsg("There was an error receiving data", e, getName())).setEphemeral(true).queue();
        }
    }


    @NotNull
    @Override
    public String getDescription() {
        return "Get an easy, medium or hard question and try to pick the correct answer from 4 choices";
    }

    @NotNull
    @Override
    public String getName() {
        return "trivia";
    }

    @NotNull
    @Override
    public ArgumentSlashCommandCount takesArguments() {
        return ArgumentSlashCommandCount.MULTIPLE;
    }

    @Nullable
    @Override
    public List<OptionData> getOptionData() {
        OptionData optionData1 = new OptionData(OptionType.STRING, Objects.requireNonNull(getArgName()).get(0),
                "Choose the difficulty of your answer, if you'd like to")
                .setAutoComplete(true).setRequired(false);
        OptionData optionData2 = new OptionData(OptionType.STRING, getArgName().get(1),
                "Choose the topic of your question, if you'd like to")
                .setAutoComplete(true).setRequired(false);
        return List.of(optionData1, optionData2);
    }

    @Nullable
    @Override
    public List<String> getArgName() {
        return List.of("difficulty", "topic");
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

    @NotNull
    @Override
    public String getButtonIdentifier() {
        return "TRIVIA-";
    }

    @Override
    public void handleEvent(@NotNull ButtonInteractionEvent event) {
        if (Objects.requireNonNull(event.getInteraction().getButton().getId()).contains("yes")) {
            event.replyEmbeds(new EmbedBuilder().setColor(Color.GREEN).setTitle(correctAnswerMsg())
                            .setDescription("You have chosen the correct answer!").build()).setEphemeral(true)
                    .addActionRow(Button.success(getButtonIdentifier() + "NEW", "Generate a new question"))
                    .queue();
        }
        if (Objects.requireNonNull(event.getInteraction().getButton().getId()).contains("no")) {
            event.replyEmbeds(new EmbedBuilder().setColor(Color.RED).setTitle("This answer is not correct")
                            .setDescription("You can try again!").build()).setEphemeral(true)
                    .addActionRow(Button.success(getButtonIdentifier() + "NEW", "I'd like a different question"))
                    .queue();
        }
        if (Objects.requireNonNull(event.getInteraction().getButton().getId()).contains("NEW")) {
            try {
                TriviaAPI api = new TriviaAPI(topic, difficulty);

                List<Button> btnList = new ArrayList<>();
                int rndN = UtilityClass.generateRandomInt(0, api.getIncorrectAnswers().size());
                for (int i = 0; i < api.getIncorrectAnswers().size(); i++)
                    btnList.add(Button.primary(getButtonIdentifier() + "no" + i, api.getIncorrectAnswers().get(i)));

                btnList.add(rndN, Button.primary(getButtonIdentifier() + "yes", api.getCorrectAnswer()));
                event.replyEmbeds(api.generateEmbed(Objects.requireNonNull(event.getMember()))
                                .build()).addActionRow(btnList).setEphemeral(true)
                        .queue();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String correctAnswerMsg() {
        String[] msgs =
                {
                        "Well done",
                        "Congrats",
                        "Congratulations",
                        "Job well done",
                        "You're amazing",
                        "You're awesome",
                        "You're on you way to become a master at this game!",
                        "Nice!"
                };

        return msgs[UtilityClass.generateRandomInt(0, msgs.length - 1)];
    }
}

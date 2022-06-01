package commands.commands_slash;

import enums_annotations_exceptions.enums.Arguments;
import enums_annotations_exceptions.exceptions.UserCannotBeReachedThroughPrivateMessageException;
import library_class.LibraryClass;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static library_class.Date.currentDateTime;
import static library_class.Date.getNormalDateAndTime;
import static net.dv8tion.jda.api.requests.ErrorResponse.CANNOT_SEND_TO_USER;

public class Poke extends PokeFrame implements ISlashCommands {


    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {

        Member taggedMember = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0))).getAsMember();
        List<Member> taggedMembersArr = new ArrayList<>(getListOfMembersTaggedIfPossible(slashEvent, getArgName().get(0)));
        String content = getMessageIfPossible(slashEvent, getArgName().get(1));
        try {
            if (taggedMember != null) {
                sendMsg(slashEvent, taggedMember, content);
            }
            if (!taggedMembersArr.isEmpty()) {
                for (Member mem : taggedMembersArr) {
                    sendMsg(slashEvent, mem, content);
                }
            }

        } catch (ContextException | UserCannotBeReachedThroughPrivateMessageException e) {
            slashEvent.reply("There's been an error while poking").queue();
        }
    }

    @Override
    public @NotNull String getDescription() {
        return "Poke a user through bot";
    }

    @Override
    public @NotNull String getName() {
        return "poke";
    }

    @Override
    public @NotNull Arguments takesArguments() {
        return Arguments.MULTIPLE;
    }

    @Override
    public List<OptionData> getOptionData() {
        return new ArrayList<>(List.of(new OptionData(OptionType.MENTIONABLE, Objects.requireNonNull(Objects.requireNonNull(getArgName()).get(0)),
                        "tag a user whom you want to POKE!", true, false),
                new OptionData(OptionType.STRING, Objects.requireNonNull(Objects.requireNonNull(getArgName()).get(1)),
                        "write custom message to the user", false, false)));
    }

    @Override
    public List<String> getArgName() {
        return new ArrayList<>(List.of("member", "custom_message"));
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }

    /**
     * Send a private message to the member from arguments;
     * if this task fails, error message will be sent out, else success message will be sent
     *
     * @param event   {@link SlashCommandInteractionEvent}
     * @param member  member to whom the message is supposed to be sent to
     * @param content content of the message <b>--can be null</b>
     * @throws ContextException                                  if the message content is not fit to be sent out
     * @throws UserCannotBeReachedThroughPrivateMessageException if user cannot be reached
     */
    private void sendMsg(SlashCommandInteractionEvent event, Member member, @Nullable String content)
            throws ContextException, UserCannotBeReachedThroughPrivateMessageException {
        if (content == null) {
            content = "Hey!\n" + Objects.requireNonNull(event.getMember()).getEffectiveName()
                    + " needs you to join " + Objects.requireNonNull(event.getGuild()).getName();
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(LibraryClass.getRandomColor());
        embedBuilder.setFooter("Sent by: " + member.getUser().getAsMention() + " from " +
                Objects.requireNonNull(event.getGuild()).getName(), member.getEffectiveAvatarUrl());
        embedBuilder.setDescription("You have been poked!");
        embedBuilder.setTitle("Hey Wake Up!");
        embedBuilder.addField("Message:", content, false);

        if (!member.getUser().isBot()) {
            member.getUser().openPrivateChannel().flatMap(privateChannel ->
                            privateChannel.sendMessageEmbeds(embedBuilder.build()))
                    .flatMap(succ -> event.getChannel().sendMessageEmbeds(new EmbedBuilder().setColor(
                                    new Color(102, 255, 0))
                            .addField("Poke has been sent!! \uD83D\uDE07",
                                    "*" + Objects.requireNonNull(event.getMember())
                                            .getUser().getName() + "* has just triggered '" + this.getName() + "' command",
                                    false)
                            .setFooter(getNormalDateAndTime(currentDateTime(true),
                                    true) + " GTM+0")
                            .build()))
                    .onErrorFlatMap(CANNOT_SEND_TO_USER::test,
                            (err) -> event.getChannel().sendMessageEmbeds(new EmbedBuilder().setColor(Color.RED)
                                    .addField("Poke has failed!! \uD83D\uDE23",
                                            "*" + Objects.requireNonNull(event.getMember())
                                                    .getUser().getName() + "* has just triggered '" + this.getName() + "' command",
                                            false)
                                    .setFooter(getNormalDateAndTime(currentDateTime(true),
                                            true) + " GTM+0")
                                    .build()))

                    .queue();

        } else {
            event.getChannel().sendMessageEmbeds(
                    new EmbedBuilder().setColor(Color.ORANGE)
                            .addField("Poke has failed - receiver is a bot!! \uD83D\uDE23",
                                    "*" + Objects.requireNonNull(event.getMember())
                                            .getUser().getName() + "* has just triggered '" + this.getName() + "' command",
                                    false)
                            .setFooter(getNormalDateAndTime(currentDateTime(true),
                                    true) + " GTM+0")
                            .build()

            ).queue();
        }


    }
}

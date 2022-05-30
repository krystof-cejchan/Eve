package commands.commands_slash;

import commands.textCommands.Support;
import enums_annotations_exceptions.enums.Arguments;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Poke extends Support implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        Member member = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()).get(0))).getAsMember();
        String content = null;
        try {
            content = slashEvent.getOption(getArgName().get(1)).getAsString();
        } catch (NullPointerException ignored) {
            //parameter was not set
            //no need to handle exception
        }


        try {
            Role role = Objects.requireNonNull(slashEvent.getOption(getArgName().get(0))).getAsRole();
            MessageBuilder builder = new MessageBuilder();
            builder.append("Poke sent to:```ini\n[");
            for (Member m : Objects.requireNonNull(slashEvent.getGuild()).getMembers()) {
                if (m.getRoles().contains(role)) {
                    System.out.println(m);

                    sendMsg(slashEvent, m, content);
                    builder.append(m.getEffectiveName()).append("\t");
                }
            }
            slashEvent.reply(builder.replaceLast("\t", "").append("]\n```").build()).queue();

        } catch (IllegalStateException ignored) {

        } catch (Exception e) {
            slashEvent.reply("There's been an error while poking").queue();
            e.printStackTrace();
        }
        try {
            if (member != null) {
                sendMsg(slashEvent, member, content);
                slashEvent.reply(member.getEffectiveName() + " was poked").queue();
            }
        } catch (ContextException e) {
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
                        "tag a user whom you want to POKE!", true, false)
                , new OptionData(OptionType.STRING, Objects.requireNonNull(Objects.requireNonNull(getArgName()).get(1)),
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

    private void sendMsg(SlashCommandInteractionEvent event, Member member, @Nullable String content)
            throws ContextException {
        if (content == null) {
            content = "Hey!\n" + Objects.requireNonNull(event.getMember()).getEffectiveName()
                    + " needs you to join " + Objects.requireNonNull(event.getGuild()).getName();
        }

        sendPrivateMessage(member.getUser(), content + "\n*//Sent & Poked by " + member.getUser().getAsMention() + " from '" +
                Objects.requireNonNull(event.getGuild()).getName() + "'*");
    }
}

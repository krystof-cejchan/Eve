package commands.commands_slash;

import commands.textCommands.Support;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.ContextException;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Poke extends Support implements ISlashCommands {
    @Override
    public void executeSlashCommand(SlashCommandInteractionEvent slashEvent) {
        Member member = Objects.requireNonNull(slashEvent.getOption(Objects.requireNonNull(getArgName()))).getAsMember();
        try {
            Role role = Objects.requireNonNull(slashEvent.getOption(getArgName())).getAsRole();
            MessageBuilder builder = new MessageBuilder();
            builder.append("Poke sent to:```ini\n[");
            for (Member m : Objects.requireNonNull(slashEvent.getGuild()).getMembers()) {
                if (m.getRoles().contains(role)) {
                    sendMsg(slashEvent, m);
                    builder.append(m.getEffectiveName()).append("\t");
                }
            }
            slashEvent.reply(builder.replaceLast("\t","").append("]\n```").build()).queue();

        } catch (IllegalStateException ignored) {

        } catch (Exception e) {
            slashEvent.reply("There's been an error while poking").queue();
            e.printStackTrace();
        }
        try {
            if (member != null) {
                sendMsg(slashEvent, member);
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
    public boolean takesArguments() {
        return true;
    }

    @Nullable
    @Override
    public OptionData getOptionData() {
        return new OptionData(OptionType.MENTIONABLE, Objects.requireNonNull(getArgName()), "tag a user whom you want to POKE!", true, false);
    }

    @Nullable
    @Override
    public String getArgName() {
        return "member";
    }

    @Override
    public boolean isGuildOnly() {
        return true;
    }

    private void sendMsg(SlashCommandInteractionEvent event, Member member) throws ContextException {
        sendPrivateMessage(member.getUser(), "Hey!\n" + Objects.requireNonNull(event.getMember()).getEffectiveName()
                + " needs you to join " + Objects.requireNonNull(event.getGuild()).getName());
    }
}

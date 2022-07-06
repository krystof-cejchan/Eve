package bin;

import commands.commands_slash.ISlashCommands;
import commands.commands_slash.autocompletion.IAutoCompletion;
import commands.commands_slash.poke.Poke;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MemberPokeAutoCompletion implements IAutoCompletion {
    @Override
    public @NotNull List<String> getStringChoices(@NotNull CommandAutoCompleteInteractionEvent event) {
        ArrayList<Member> members = new ArrayList<>(Objects.requireNonNull(event.getGuild()).getMembers());
        ArrayList<String> choices = new ArrayList<>();
        members.forEach(member -> choices.add(member.getAsMention()));
        //choices.removeIf(choice->choice.equals(Objects.requireNonNull(event.getMember()).getEffectiveName()));
        return choices;
    }

    @Override
    public @NotNull ISlashCommands representativeCommand() {
        return new Poke();
    }
}

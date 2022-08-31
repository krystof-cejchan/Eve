package cz.krystofcejchan.commands.commands_slash.poke;

import cz.krystofcejchan.commands.commands_slash.SlashCommandManager;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Extension to {@link Poke}
 */
public class PokeFrame {
    protected String getMessageIfPossible(SlashCommandInteractionEvent slashEvent, String argName) throws NullPointerException {
        return slashEvent.getOptions()
                .stream()
                .map(OptionMapping::getName)
                .anyMatch(m -> m.contains(argName)) ?
                Objects.requireNonNull(slashEvent.getOption(argName)).toString() : null;
    }

    protected List<Member> getListOfMembersTaggedIfPossible(SlashCommandInteractionEvent slashEvent, String argName) {
        List<Member> memberList = new ArrayList<>();
        try {
            Role role = Objects.requireNonNull(slashEvent.getOption(argName)).getAsRole();
            for (Member m : Objects.requireNonNull(slashEvent.getGuild()).getMembers()) {
                if (m.getRoles().contains(role))
                    memberList.add(m);
            }

        } catch (IllegalStateException ignored) {
            // no role was tagged
        } catch (Exception e) {
            slashEvent.replyEmbeds(SlashCommandManager.generateErrorMsg("There's been an error while poking", e, this.getClass().getName())).setEphemeral(true).queue();
            e.printStackTrace();
        }
        return memberList;
    }


}

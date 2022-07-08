package cz.krystofcejchan.commands.commands_slash.poke;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Extension to {@link Poke}
 */
public class PokeFrame {
    protected String getMessageIfPossible(SlashCommandInteractionEvent slashEvent, String argName) throws NullPointerException {
        try {
            return slashEvent.getOption(argName).getAsString();
        } catch (NullPointerException ignored) {
            return null;
          /* parameter was not set
            no need to handle exception*/
        }
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
            slashEvent.reply("There's been an error while poking").queue();
            e.printStackTrace();
        }
        return memberList;
    }


}

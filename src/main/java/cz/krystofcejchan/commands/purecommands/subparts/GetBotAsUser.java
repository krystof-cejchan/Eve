package cz.krystofcejchan.commands.purecommands.subparts;

import cz.krystofcejchan.main.Main;
import net.dv8tion.jda.api.entities.User;

public class GetBotAsUser {
    public static User getBot() {
        return Main.publicJDA.getSelfUser();
    }
}

package objects;

import net.dv8tion.jda.api.entities.Member;

public class MemeberWhoTriggeredEchoCommand {
    public static Member member;

    public static void setMember(Member m) {
        member = m;
    }

    public static Member getMember() {
        return member;
    }
}

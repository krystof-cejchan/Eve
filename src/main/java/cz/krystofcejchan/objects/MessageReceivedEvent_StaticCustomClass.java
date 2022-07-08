package cz.krystofcejchan.objects;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageReceivedEvent_StaticCustomClass {
    private static MessageReceivedEvent event;

    public static MessageReceivedEvent getEvent() {
        return MessageReceivedEvent_StaticCustomClass.event;
    }

    public static void setEvent(MessageReceivedEvent event) {
        MessageReceivedEvent_StaticCustomClass.event = event;
    }
}

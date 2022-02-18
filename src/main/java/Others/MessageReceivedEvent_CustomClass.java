package Others;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageReceivedEvent_CustomClass {
	private MessageReceivedEvent event;

	public MessageReceivedEvent_CustomClass(MessageReceivedEvent event) {
		this.setEvent(event);
	}

	public MessageReceivedEvent getEvent() {
		return event;
	}

	public void setEvent(MessageReceivedEvent event) {
		this.event = event;
	}

}

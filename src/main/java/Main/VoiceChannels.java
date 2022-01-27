package Main;

import java.util.List;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
//import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class VoiceChannels {
	
	public void Join(MessageReceivedEvent event)
	{
	AudioChannel channel =event.getMember().getVoiceState().getChannel();
	if(channel != null) {
	if(!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
	  
	    event.getChannel().sendMessage("equal rights to all bots!!! - pls gimme permission to join ya").queue();
	    return;
	}

	 AudioChannel connectedChannel = event.getMember().getVoiceState().getChannel();
	System.out.println(connectedChannel);
	
	AudioManager audioManager = event.getGuild().getAudioManager();

	/*if(audioManager.isAttemptingToConnect()) {
		event.getChannel().sendMessage("The bot is already trying to connect! Enter the chill zone!").queue();
	    return;
	}*/

	audioManager.openAudioConnection(connectedChannel);

	event.getChannel().sendMessage("Connected to the voice channel!").queue();
	}
	
	}
	
	
	public void JoinChannel(MessageReceivedEvent event, String channelName)
	{
		try {
	List<VoiceChannel> channel1 = event.getGuild().getVoiceChannelsByName(channelName, true);
	if(channel1 != null) {
	AudioChannel channel = channel1.get(0);
	
	if(!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
	  
	    event.getChannel().sendMessage("equal rights to all bots!!! - pls gimme permission to join ya").queue();
	    return;
	}

	
	
	AudioManager audioManager = event.getGuild().getAudioManager();

	/*if(audioManager.isAttemptingToConnect()) {
		event.getChannel().sendMessage("The bot is already trying to connect! Enter the chill zone!").queue();
	    return;
	}*/

	audioManager.openAudioConnection(channel);

	event.getChannel().sendMessage("Connected to the voice channel!").queue();
	}
	}
		catch(Exception e) {
			event.getChannel().sendMessage("destination unknown").queue();
	}
	}
	
	
	
	
	public void Leave(MessageReceivedEvent event)
	 {
	 AudioChannel connectedChannel = event.getGuild().getSelfMember().getVoiceState().getChannel();
    
	 System.out.println(connectedChannel);
    if(connectedChannel == null) {
        
        event.getMessage().reply("?").queue();
        return;
    }
  
    event.getGuild().getAudioManager().closeAudioConnection();
    
    event.getChannel().sendMessage("Imma head out").queue();
	
}
	
	
}

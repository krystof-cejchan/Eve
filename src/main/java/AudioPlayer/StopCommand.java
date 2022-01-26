package AudioPlayer;

import javax.annotation.Nullable;

import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class StopCommand {
	public void pauseMusic(MessageReceivedEvent event) {
	
	//	event.getChannel();
		final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
	@Nullable	AudioChannel connectedChannel =event.getMember().getVoiceState().getChannel(); //user
		

		@Nullable AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); //bot
		
		// new VoiceChannels();
		
		 try {
			 if(!(connectedChannel==(null)) || !(connectedChannelSelf==(null))) {
					//uživatel někde je a bot taky
					if(connectedChannel.equals(connectedChannelSelf)) {
						
						
						if(!musicManager.SCHEDULER.PLAYER.isPaused()) {
							musicManager.SCHEDULER.PLAYER.setPaused(true);;
						}
						
						
					}

				}
		} catch (Exception e) {
			System.out.println(e);
		}
			

		
		
	}
	
	public void stopMusic(MessageReceivedEvent event) {
		final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
		@Nullable	AudioChannel connectedChannel =event.getMember().getVoiceState().getChannel(); //user
			

			@Nullable AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); //bot
		try {
			 if(!(connectedChannel==(null)) || !(connectedChannelSelf==(null))) {
					//uživatel někde je a bot taky
					if(connectedChannel.equals(connectedChannelSelf)) {
						
						musicManager.SCHEDULER.PLAYER.stopTrack();
						musicManager.SCHEDULER.QUEUE.clear();
						musicManager.SCHEDULER.PLAYER.destroy();
						
						
					}

				}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}

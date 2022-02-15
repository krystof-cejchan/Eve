package Main;

import java.util.ArrayList;
import AudioPlayer.StopCommand;
import Commands.CommandManager;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

	private boolean allower = true;
	Prefix prefixClass = new Prefix();
	String prefix = this.prefixClass.getValue();

	public StartUp startUp = new StartUp();
	String limit = "3";
	GifSender gifs = new GifSender();

	public void sendGif(String key, String limit, MessageReceivedEvent event) {
		try {

			String zprava = gifs.call_me(key, limit);
			event.getMessage().reply((zprava)).queue();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		try {
			leaveIfAlone(event, null, true);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
		try {
			leaveIfAlone(null, event, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void onMessageReceived(MessageReceivedEvent event) {

		try {
			if (event.getAuthor().isBot() == false) {

				TextChannel re = event.getGuild().getTextChannelById("933515864790159360");
				MessageChannel eventChannel = event.getChannel();
				CurrentTextChannel ctch = new CurrentTextChannel(eventChannel.getId());
				ctch.setIid(eventChannel.getId());
				if (eventChannel == re || allower) {
					if (event.getMessage().getContentRaw().startsWith(prefix)) {
						CommandManager manager = new CommandManager();
						if (manager.getCommand(event) != null)
							manager.getCommand(event).doTask(event);
					}

				}
			}
		} catch (Exception e) {
			// command was not found
			e.printStackTrace();
		}
	}

	private void leaveIfAlone(GuildVoiceLeaveEvent event, GuildVoiceMoveEvent event2, boolean leave) {
		if (leave) {
			AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel();

			ArrayList<Member> members = new ArrayList<>(connectedChannelSelf.getMembers());

			boolean human = false;
			for (Member member : members) {
				if (member.getUser().isBot() == false) {
					human = true;
				}
			}
			if (!human) {
				VoiceChannels leaveVC = new VoiceChannels();
				StopCommand stop = new StopCommand();
				stop.stopMusic(event);
				leaveVC.Leave(event);
			}
		} else {
			AudioChannel connectedChannelSelf = event2.getGuild().getSelfMember().getVoiceState().getChannel();

			ArrayList<Member> members = new ArrayList<>(connectedChannelSelf.getMembers());

			boolean human = false;
			for (Member member : members) {
				if (member.getUser().isBot() == false) {
					human = true;
				}
			}
			if (!human) {
				VoiceChannels leaveVC = new VoiceChannels();
				StopCommand stop = new StopCommand();
				stop.stopMusic(event2);
				leaveVC.Leave(event2);
			}
		}

	}

}

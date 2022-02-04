package Speech_Texts_Listening;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Echo {
	static double volume = 1f;
	static boolean isAllowedbyUser = false;

	public void onEchoCommand(MessageReceivedEvent event) {

		Member member = event.getMember();

		GuildVoiceState voiceState = member.getVoiceState();
		AudioChannel channel = voiceState.getChannel();
		if (channel != null) {
			connectTo(channel);
			onConnecting(channel, event.getChannel());
			isAllowedbyUser = true;
		} else {
			onUnknownChannel(event.getChannel(), "your voice channel");
		}
	}

	public void stopEchoing() {
		isAllowedbyUser = false;
		AudioManager audioManager = guild.getAudioManager();

		// audioManager.setSendingHandler();

		audioManager.setReceivingHandler(null);
		// reset audiosource to null + reset sending audio
	}

	public void setVolume(int vol) {
		double toDouble = Double.valueOf(vol);
		volume = toDouble / 100;
	}

	private void onConnecting(AudioChannel channel, MessageChannel messageChannel) {
		messageChannel.sendMessage("Connecting to " + channel.getName()).queue();
	}

	private void onUnknownChannel(MessageChannel channel, String comment) {
		channel.sendMessage("Unable to connect to ``" + comment + "``, no such channel!").queue();

	}

	static Guild guild;

	private void connectTo(AudioChannel channel) {
		guild = channel.getGuild();

		AudioManager audioManager = guild.getAudioManager();

		EchoHandler handler = new EchoHandler();

		audioManager.setSendingHandler(handler);

		audioManager.setReceivingHandler(handler);

		audioManager.openAudioConnection(channel);

	}

	public static class EchoHandler implements AudioSendHandler, AudioReceiveHandler {

		private final Queue<byte[]> queue = new ConcurrentLinkedQueue<>();

		public boolean canReceiveCombined() {
			if (queue.size() < 10 || isAllowedbyUser)
				return true;

			else {
				return false;
			}
		}

		ArrayList<User> speakingUsers = new ArrayList<>();

		@Override
		public void handleCombinedAudio(CombinedAudio combinedAudio) {

			try {
				byte[] data = combinedAudio.getAudioData(volume);

				/*
				 * speakingUsers.addAll(combinedAudio.getUsers());
				 * System.out.println(speakingUsers.size());
				 */
				queue.add(data);
			} catch (Exception e) {
				System.out.println(e);
			}

		}

		@Override
		public boolean canProvide() {

			return !queue.isEmpty();
		}

		@Override
		public ByteBuffer provide20MsAudio() {

			byte[] data = queue.poll();
			return data == null ? null : ByteBuffer.wrap(data);
		}

		@Override
		public boolean isOpus() {

			return false;
		}

	}
}

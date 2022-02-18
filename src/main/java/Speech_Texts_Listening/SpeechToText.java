package Speech_Texts_Listening;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import ListeningCommands.IListeningCommands;
import ListeningCommands.ListeningCommandManager;
import Main.CurrentTextChannel;
import Others.MessageReceivedEvent_CustomClass;

import javax.sound.sampled.AudioFileFormat;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SpeechToText {
	public static MessageReceivedEvent_CustomClass msgEvent;

	public static class Language {
		private static String lang = "en-GB";

		public static void setLang(String language) {
			lang = language;
		}

		public static String getLang() {
			return lang;
		}
	}

	public static String channelId;

	public static String getChannelId() {
		return channelId;
	}

	public void onEchoCommand(MessageReceivedEvent event) {

		Member member = event.getMember();
		msgEvent = new MessageReceivedEvent_CustomClass(event);
		EchoHandler echoH = new EchoHandler();
		echoH.isAllowedToCarryOn = true;
		rescievedBytes.clear();
		channelId = event.getChannel().getId();
		GuildVoiceState voiceState = member.getVoiceState();
		AudioChannel channel = voiceState.getChannel(); // user
		AudioChannel connectedChannelSelf = event.getGuild().getSelfMember().getVoiceState().getChannel(); // bot
		if (channel != null || connectedChannelSelf.equals(channel)) {
			connectTo(channel);
			onConnecting(channel, event.getChannel());
		} else {
			onUnknownChannel(event.getChannel(), "your voice channel");
		}
	}

	private static void getWavFile(File outFile, byte[] decodedData) throws IOException {
		AudioFormat format = new AudioFormat(48000.0f, 16, 2, true, true);

		AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(decodedData), format, decodedData.length),
				AudioFileFormat.Type.WAVE, outFile);
	}

	private static String text = "";

	public static void setText(String txt) {
		text = txt;
	}

	public static String getText() {
		return text;
	}

	public String getTranscription() {
		String s;

		try {

			Process p = Runtime.getRuntime().exec(
					"py C:\\Users\\kryst\\git\\repository3\\discordbottest\\src\\main\\java\\External_Files\\soundfiletotext.py H:\\audio_file.wav "
							+ Language.lang);
			// Process p = Runtime.getRuntime().exec("py
			// https://github.com/TheKrystof701/Discord-Java-Bot/blob/master/src/main/java/External_Files/soundfiletotext.py
			// H:\\audio_file.wav");
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			new BufferedReader(new InputStreamReader(p.getErrorStream()));

			String vysledek = "";
			while ((s = stdInput.readLine()) != null) {
				vysledek = s;
			}
			return vysledek;

		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			return "Error has occured";
		}
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

		// audioManager.setSendingHandler(handler);

		audioManager.setReceivingHandler(handler);

		audioManager.openAudioConnection(channel);
	}

	private static List<byte[]> rescievedBytes = new ArrayList<>();

	public static class EchoHandler implements AudioSendHandler, AudioReceiveHandler {

		private final Queue<byte[]> queue = new ConcurrentLinkedQueue<>();

		public boolean canReceiveCombined() {

			return queue.size() < 10;
		}

		ArrayList<Integer> talkingMembersCount = new ArrayList<Integer>();
		int MAX_VALUE = 100;
		public boolean isAllowedToCarryOn = true;

		@Override
		public void handleCombinedAudio(CombinedAudio combinedAudio) {

			/*
			 * if (combinedAudio.getUsers().isEmpty()) return;
			 */

			guild.getAudioManager();

			// byte[] data = combinedAudio.getAudioData(1.0f); // volume at 100% = 1.0 (50%
			// = 0.5 / 55% = 0.55)
			rescievedBytes.add(combinedAudio.getAudioData(1.5f));

			talkingMembersCount.add(combinedAudio.getUsers().size());
			// queue.add(data);

			if (talkingMembersCount.size() > MAX_VALUE) {
				if (isAllowedToCarryOn && haveUsersStoppedTalking(talkingMembersCount)) {
					try {
						System.out.println(combinedAudio.getUsers().size());
						int size = 0;
						for (byte[] bs : rescievedBytes) {
							size += bs.length;
						}
						byte[] decodedData = new byte[size];
						int i = 0;
						for (byte[] bs : rescievedBytes) {
							for (int j = 0; j < bs.length; j++) {
								decodedData[i++] = bs[j];
							}
						}
						File file = new File("H:\\audio_file.wav");

						getWavFile(file, decodedData);
						SpeechToText StT = new SpeechToText();
						String transcription = StT.getTranscription();
						System.out.println(transcription);
						// CurrentTextChannel ctch = new CurrentTextChannel();
						guild.getTextChannelById(CurrentTextChannel.getId()).sendMessage(transcription).queue();
						SpeechToText.setText(transcription);
						ListeningCommandManager listeningCommandManager = new ListeningCommandManager();

						IListeningCommands command = listeningCommandManager.getCommand(transcription);
						command.doTask(msgEvent.getEvent());

						System.out.println("I have just executed " + command.getName());

						// audioManager.closeAudioConnection();
						if (haveUsersStoppedTalking(talkingMembersCount))
							isAllowedToCarryOn = false;

						System.out.println("its done aint it");
					} catch (Exception e) {
						System.out.println(e);
					}

				}
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

		private boolean haveUsersStoppedTalking(ArrayList<Integer> list) {

			int count = 0;
			try {
				for (int i = list.size() - MAX_VALUE; i < list.size(); i++) {

					count = count + list.get(i);
				}

				if (count == 0)
					return true;
				else {
					return false;
				}
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}

		}
	}
}

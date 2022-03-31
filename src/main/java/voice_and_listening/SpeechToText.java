package voice_and_listening;

import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import objects.CurrentTextChannel;
import objects.MessageReceivedEvent_CustomClass;
import objects.ScriptPathPointer;
import objects.SoundFile;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import _library_class.LibraryClass;
import commands_voice.IListeningCommands;
import commands_voice.ListeningCommandManager;

import javax.sound.sampled.AudioFileFormat;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
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
		if (channel != null) {
			if (!channel.equals(connectedChannelSelf)) {
				connectTo(channel);
				onConnecting(channel, event.getChannel());
			} else {
				connectTo(channel);
			}

		} else {
			event.getChannel().sendMessage("You are nowhere to be found *sad noises*").queue();
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

		String rawString = LibraryClass.runPyScript(ScriptPathPointer.soundFile2Text,
				SoundFile.getWholePath() + " " + Language.lang);
		byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);

		return new String(bytes, StandardCharsets.UTF_8);

	}

	private void onConnecting(AudioChannel channel, MessageChannel messageChannel) {
		messageChannel.sendMessage("Connecting to " + channel.getName()).queue();
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
		// ArrayList<Boolean> talkingMemeberGuard = new ArrayList<>();
		final int MAX_VALUE = 100;
		public boolean isAllowedToCarryOn = true;

		@Override
		public void handleCombinedAudio(CombinedAudio combinedAudio) {
			// includeUserInCombinedAudio(msgEvent.getEvent().getAuthor());

			guild.getAudioManager();

			rescievedBytes.add(combinedAudio.getAudioData(1.5f));// 1.0 → 100%

			if (combinedAudio.getUsers().contains(msgEvent.getEvent().getAuthor()))
				talkingMembersCount.add(1);

			else
				talkingMembersCount.add(0);

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

						SoundFile.setTitle(msgEvent.getEvent().getGuild().getId());

						File file = new File(SoundFile.getWholePath());

						getWavFile(file, decodedData);
						SpeechToText StT = new SpeechToText();
						String transcription = StT.getTranscription();
						guild.getTextChannelById(CurrentTextChannel.getId()).sendMessage(transcription).queue();
						if (!((SpeechToText.Language.getLang().equals("en-GB")
								|| SpeechToText.Language.getLang().equals("en-US")))) {
							transcription = LibraryClass.runPyScript(ScriptPathPointer.translator, transcription);
							guild.getTextChannelById(CurrentTextChannel.getId()).sendMessage(transcription).queue();
						}

						System.out.println(transcription); // CurrentTextChannel ctch = new CurrentTextChannel();

						SpeechToText.setText(transcription);
						ListeningCommandManager listeningCommandManager = new ListeningCommandManager();
						// System.out.println("");
						IListeningCommands command = listeningCommandManager.getCommand(transcription);
						if (command != null)
							command.doTask(msgEvent.getEvent());

						else {

							msgEvent.getEvent().getMessage().reply(
									"There's been an error\nCommand either does not exist or I couldn't understand you")
									.queue();
						}

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

		// if (combinedAudio.getUsers().contains(msgEvent.getEvent().getAuthor()))

		// talkingMembersCount.add(1); System.out.println("mluvíš"); }

		/*
		 * /*@Override public void handleUserAudio(@Nonnull UserAudio userAudio) {
		 * GuildVoiceState voiceState = msgEvent.getEvent().getMember().getVoiceState();
		 * 
		 * if (userAudio.getUser().equals(msgEvent.getEvent().getAuthor())) {
		 * guild.getAudioManager();
		 * 
		 * rescievedBytes.add(userAudio.getAudioData(1.5f));// 1.0 → 100%
		 * 
		 * if (talkingMembersCount.size() > MAX_VALUE) { if (isAllowedToCarryOn &&
		 * haveUsersStoppedTalking(talkingMembersCount)) { try {
		 * 
		 * // System.out.println(combinedAudio.getUsers().size()); int size = 0; for
		 * (byte[] bs : rescievedBytes) { size += bs.length; } byte[] decodedData = new
		 * byte[size]; int i = 0; for (byte[] bs : rescievedBytes) { for (int j = 0; j <
		 * bs.length; j++) { decodedData[i++] = bs[j]; } }
		 * 
		 * SoundFile.setTitle(msgEvent.getEvent().getGuild().getId());
		 * 
		 * File file = new File(SoundFile.getWholePath());
		 * 
		 * getWavFile(file, decodedData); SpeechToText StT = new SpeechToText(); String
		 * transcription = StT.getTranscription();
		 * guild.getTextChannelById(CurrentTextChannel.getId()).sendMessage(
		 * transcription).queue(); if
		 * (!((SpeechToText.Language.getLang().equals("en-GB") ||
		 * SpeechToText.Language.getLang().equals("en-US")))) { transcription =
		 * LibraryClass.runPyScript(ScriptPathPointer.translator, transcription);
		 * guild.getTextChannelById(CurrentTextChannel.getId()).sendMessage(
		 * transcription).queue(); }
		 * 
		 * System.out.println(transcription); // CurrentTextChannel ctch = new
		 * CurrentTextChannel();
		 * 
		 * SpeechToText.setText(transcription); ListeningCommandManager
		 * listeningCommandManager = new ListeningCommandManager();
		 * 
		 * IListeningCommands command =
		 * listeningCommandManager.getCommand(transcription); if (command != null)
		 * command.doTask(msgEvent.getEvent());
		 * 
		 * else {
		 * 
		 * msgEvent.getEvent().getMessage().reply(
		 * "There's been an error\nCommand either does not exist or I couldn't understand you"
		 * ) .queue(); }
		 * 
		 * // audioManager.closeAudioConnection(); if
		 * (haveUsersStoppedTalking(talkingMembersCount)) isAllowedToCarryOn = false;
		 * 
		 * System.out.println("its done aint it"); } catch (Exception e) {
		 * System.out.println(e); }
		 * 
		 * } } }
		 * 
		 * }
		 */

		@Override
		public boolean includeUserInCombinedAudio(User user) {
			if (user.equals(msgEvent.getEvent().getAuthor()))
				return true;

			return false;
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

		/*
		 * @Override public boolean canReceiveUser() { // TODO Auto-generated method
		 * stub return AudioReceiveHandler.super.canReceiveUser(); }
		 */

		/**
		 * Checks if users have stopped talking
		 * 
		 * @author krystof-cejchan
		 * 
		 * @param list of talking members {@link #talkingMembersCount}
		 * @return true if last {@link #MAX_VALUE} (100 as default) is 0 <br>
		 *         else false
		 */
		private boolean haveUsersStoppedTalking(ArrayList<Integer> list) {

			int count = 0;
			try {
				for (int y = list.size() - MAX_VALUE; y < list.size(); y++) {

					count += list.get(y);
				}

				if (count == 0)
					return true;

			} catch (Exception e) {
				System.out.println(e);

			}
			return false;

		}

	}
}

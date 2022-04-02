package voice.voice_and_listening;

import _library_class.Global_Values;
import _library_class.LibraryClass;
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
import voice.commands_voice.IListeningCommands;
import voice.commands_voice.ListeningCommandManager;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SpeechToText {
    public static MessageReceivedEvent_CustomClass msgEvent;
    public static String channelId;
    static Guild guild;
    private static String text = "";
    private static final List<byte[]> rescievedBytes = new ArrayList<>();

    public static String getChannelId() {
        return channelId;
    }

    private static void getWavFile(File outFile, byte[] decodedData) throws IOException {
        AudioFormat format = new AudioFormat(48000.0f, 16, 2, true, true);

        AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(decodedData), format, decodedData.length), AudioFileFormat.Type.WAVE, outFile);
    }

    public static String getText() {
        return text;
    }

    public static void setText(String txt) {
        text = txt;
    }

    public void onEchoCommand(MessageReceivedEvent event) {

        Member member = event.getMember();
        msgEvent = new MessageReceivedEvent_CustomClass(event);
        EchoHandler echoH = new EchoHandler();
        echoH.isAllowedToCarryOn = true;
        rescievedBytes.clear();
        channelId = event.getChannel().getId();
        assert member != null;
        GuildVoiceState voiceState = member.getVoiceState();
        assert voiceState != null;
        AudioChannel channel = voiceState.getChannel(); // user
        AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember().getVoiceState()).getChannel(); // bot
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

    public String getTranscription() {

        String rawString = LibraryClass.runPyScript(ScriptPathPointer.soundFile2Text, SoundFile.getWholePath() + " " + Language.lang);
        assert rawString != null;
        byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);

        return new String(bytes, StandardCharsets.UTF_8);

    }

    private void onConnecting(AudioChannel channel, MessageChannel messageChannel) {
        messageChannel.sendMessage("Connecting to " + channel.getName()).queue();
    }

    private void connectTo(AudioChannel channel) {
        guild = channel.getGuild();

        AudioManager audioManager = guild.getAudioManager();

        EchoHandler handler = new EchoHandler();

        // audioManager.setSendingHandler(handler);

        audioManager.setReceivingHandler(handler);

        audioManager.openAudioConnection(channel);
    }

    public static class Language {
        private static String lang = "en-GB";

        public static String getLang() {
            return lang;
        }

        public static void setLang(String language) {
            lang = language;
        }
    }

    public static class EchoHandler implements AudioSendHandler, AudioReceiveHandler {

        // ArrayList<Boolean> talkingMemeberGuard = new ArrayList<>();
        final int MAX_VALUE = Global_Values.MAX_VALUE;
        private final Queue<byte[]> queue = new ConcurrentLinkedQueue<>();
        public boolean isAllowedToCarryOn = true;
        ArrayList<Integer> talkingMembersCount = new ArrayList<>();

        public boolean canReceiveCombined() {

            return queue.size() < 10;
        }

        @Override
        public void handleCombinedAudio(CombinedAudio combinedAudio) {
            // includeUserInCombinedAudio(msgEvent.getEvent().getAuthor());

            guild.getAudioManager();

            rescievedBytes.add(combinedAudio.getAudioData(1.5f));// 1.0 → 100%

            if (combinedAudio.getUsers().contains(msgEvent.getEvent().getAuthor())) talkingMembersCount.add(1);

            else talkingMembersCount.add(0);

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
                            for (byte b : bs) {
                                decodedData[i++] = b;
                            }
                        }

                        SoundFile.setTitle(msgEvent.getEvent().getGuild().getId());

                        File file = new File(SoundFile.getWholePath());

                        getWavFile(file, decodedData);
                        SpeechToText StT = new SpeechToText();
                        String transcription = StT.getTranscription();
                        Objects.requireNonNull(guild.getTextChannelById(CurrentTextChannel.getId())).sendMessage(transcription).queue();
                        if (!((SpeechToText.Language.getLang().equals("en-GB") || SpeechToText.Language.getLang().equals("en-US")))) {
                            transcription = LibraryClass.runPyScript(ScriptPathPointer.translator, transcription);
                            assert transcription != null;
                            Objects.requireNonNull(guild.getTextChannelById(CurrentTextChannel.getId())).sendMessage(transcription).queue();
                        }

                        System.out.println(transcription); // CurrentTextChannel ctch = new CurrentTextChannel();

                        SpeechToText.setText(transcription);
                        ListeningCommandManager listeningCommandManager = new ListeningCommandManager();
                        // System.out.println("");
                        IListeningCommands command = listeningCommandManager.getCommand(transcription);
                        if (command != null) command.doTask(msgEvent.getEvent());

                        else {

                            msgEvent.getEvent().getMessage().reply("There's been an error\nCommand either does not exist or I couldn't understand you").queue();
                        }

                        // audioManager.closeAudioConnection();
                        if (haveUsersStoppedTalking(talkingMembersCount)) isAllowedToCarryOn = false;

                        System.out.println("its done aint it");
                    } catch (Exception e) {
                        e.printStackTrace();
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
            return user.equals(msgEvent.getEvent().getAuthor());
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
         * @Override public boolean canReceiveUser() {          * stub return AudioReceiveHandler.super.canReceiveUser(); }
         */

        /**
         * Checks if users have stopped talking
         *
         * @param list of talking members {@link #talkingMembersCount}
         * @return true if last {@link #MAX_VALUE} (100 as default) is 0 <br>
         * else false
         * @author krystof-cejchan
         */
        private boolean haveUsersStoppedTalking(ArrayList<Integer> list) {

            int count = 0;
            try {
                for (int y = list.size() - MAX_VALUE; y < list.size(); y++) {

                    count += list.get(y);
                }

                if (count == 0) return true;

            } catch (Exception e) {
                e.printStackTrace();

            }
            return false;

        }

    }
}

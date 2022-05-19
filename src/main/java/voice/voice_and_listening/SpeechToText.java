package voice.voice_and_listening;

import _library_class.GlobalValues;
import commands.commands_voice.IListeningCommands;
import commands.commands_voice.ListeningCommandManager;
import database_SQLite.file_database.queries.InsertValuesToTable;
import main.StartUp;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import objects.CurrentTextChannel;
import objects.MessageReceivedEvent_CustomClass;
import objects.ScriptPathPointer;
import objects.sound_files.DeleteSoundAudioFilesFromSystemAndDatabase;
import objects.sound_files.SoundFile;
import org.jetbrains.annotations.NotNull;
import voice.PythonASCII_Decoding;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static _library_class.LibraryClass.runPyScript;

public class SpeechToText {
    private static final List<byte[]> rescievedBytes = new ArrayList<>();
    public static MessageReceivedEvent_CustomClass msgEvent;
    public static String channelId;
    static Guild guild;
    private static String text = "";

   /* public static String getChannelId() {
        return channelId;
    }*/

    private static void getWavFile(File outFile, byte[] decodedData) throws IOException, SQLException,
            ClassNotFoundException {
        /*
        float sampleRate = 16000; // 8000,11025,16000,22050,44100,48000
        int sampleSizeInBits = 16; // 8,16
        int channels = 1; // 1,2
        boolean signed = true; // true,false
        boolean bigEndian = false; // true,false
        AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        */
        AudioFormat format = new AudioFormat(48000f, 16, 2, true, true);

        AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(decodedData), format, decodedData.length),
                AudioFileFormat.Type.WAVE, outFile);

        new InsertValuesToTable().insertValuesToTable(outFile.getAbsolutePath());
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
        AudioChannel connectedChannelSelf = Objects.requireNonNull(event.getGuild().getSelfMember()
                .getVoiceState()).getChannel(); // bot
        if (channel != null) {
            if (!channel.equals(connectedChannelSelf)) {
                connectTo(channel);
                onConnecting(channel, event.getChannel());
            } else {
                connectTo(channel);
            }


            AudioManager audioManager = channel.getGuild().getAudioManager();
            if (audioManager.getReceivingHandler() != null) {
                event.getChannel().sendMessage("I'm listening ...").queue();
            } else {
                boolean sentMsg = false;
                while (audioManager.getReceivingHandler() == null) {
                    if (!sentMsg) {
                        event.getChannel().sendMessage("Warming up, gimme a moment").queue();
                        sentMsg = true;
                    }
                }
                event.getChannel().sendMessage("I'm listening ...").queue();
            }

        } else {
            event.getChannel().sendMessage("You are nowhere to be found *sad noises*").queue();
        }
    }

    public void onEchoSlashCommand(SlashCommandInteractionEvent event) throws NullPointerException {

        MessageReceivedEvent messageReceivedEvent = new MessageReceivedEvent(StartUp.publicJDA,1,
                event.getMessageChannel().retrieveMessageById(event.getMessageChannel().getLatestMessageId()).complete());
        Member member = event.getMember();
        msgEvent = new MessageReceivedEvent_CustomClass(messageReceivedEvent);
        EchoHandler echoH = new EchoHandler();
        echoH.isAllowedToCarryOn = true;
        rescievedBytes.clear();
        channelId = event.getChannel().getId();
        assert member != null;
        GuildVoiceState voiceState = member.getVoiceState();
        assert voiceState != null;
        AudioChannel channel = voiceState.getChannel(); // user
        AudioChannel connectedChannelSelf = Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getSelfMember()
                .getVoiceState()).getChannel(); // bot
        if (channel != null) {
            if (!channel.equals(connectedChannelSelf)) {
                connectTo(channel);
                onConnecting(channel, event.getChannel());
            } else
                connectTo(channel);


            AudioManager audioManager = channel.getGuild().getAudioManager();
            if (audioManager.getReceivingHandler() != null) {
                event.getChannel().sendMessage("I'm listening ...").queue();
            } else {
                boolean sentMsg = false;
                while (audioManager.getReceivingHandler() == null) {
                    if (!sentMsg) {
                        event.getChannel().sendMessage("Warming up, gimme a moment").queue();
                        sentMsg = true;
                    }
                }
                event.getChannel().sendMessage("I'm listening ...").queue();
            }

        } else {
            event.getChannel().sendMessage("You are nowhere to be found *sad noises*").queue();
        }
    }

    public String getTranscription() {
        try {
            if (PythonASCII_Decoding.decodeASCIItext(runPyScript(ScriptPathPointer.soundFile2Text,
                    SoundFile.getWholePath() + " " + Language.lang)) == null)
                return null;
            String rawString = PythonASCII_Decoding.decodeASCIItext(runPyScript(ScriptPathPointer.soundFile2Text,
                    SoundFile.getWholePath() + " " + Language.lang));
            assert rawString != null;
            byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);

            //DeleteSoundAudioFilesFromSystemAndDatabase.delete1SoundFileFromSystemAndDatabase(SoundFile.getWholePath());

            return new String(bytes, StandardCharsets.UTF_8);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            return null;
        }
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

        final Timer TIMER = new Timer();
        final int MAX_VALUE = GlobalValues.MAX_VALUE;
        final int MAX_SEC_AUDIO_RECORDING = GlobalValues.MAX_SEC_AUDIO_RECORDING;
        private final Queue<byte[]> queue = new ConcurrentLinkedQueue<>();
        public boolean isAllowedToCarryOn = true;
        ArrayList<Boolean> talkingMembersCount = new ArrayList<>();


        public boolean canReceiveCombined() {
            return queue.size() < 10;
        }

        @Override
        public void handleCombinedAudio(@NotNull CombinedAudio combinedAudio) {
            // includeUserInCombinedAudio(msgEvent.getEvent().getAuthor());
            if (isAllowedToCarryOn) {
                guild.getAudioManager();

                rescievedBytes.add(combinedAudio.getAudioData(1.12f));// 1.0 → 100%

                if (combinedAudio.getUsers().contains(msgEvent.getEvent().getAuthor())) talkingMembersCount.add(true);

                else talkingMembersCount.add(false);

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
                            final String transcription_original = StT.getTranscription();
                            String transcription_finalVersion;

                            Objects.requireNonNull(guild.getTextChannelById(CurrentTextChannel.getId()))
                                    .sendMessage(transcription_original).queue();
                            if (!((SpeechToText.Language.getLang().equals("en-GB") || SpeechToText.Language.getLang()
                                    .equals("en-US")))) {
                                transcription_finalVersion = runPyScript(ScriptPathPointer.translator, transcription_original);
                                assert transcription_finalVersion != null;
                                Objects.requireNonNull(guild.getTextChannelById(CurrentTextChannel.getId()))
                                        .sendMessage(transcription_finalVersion).queue();
                            } else {
                                transcription_finalVersion = transcription_original;
                            }

                            System.out.println(transcription_finalVersion);

                            SpeechToText.setText(transcription_finalVersion);
                            ListeningCommandManager listeningCommandManager = new ListeningCommandManager();

                            IListeningCommands command = listeningCommandManager.getCommand(transcription_finalVersion);

                            if (command != null) {
                                if (command.isParamRequired()) {

                                    if (!transcription_original.isEmpty() && !transcription_original.isBlank())
                                        command.doTask(msgEvent.getEvent(), transcription_original);

                                } else command.doTask(msgEvent.getEvent(), null);
                            } else {
                                msgEvent.getEvent().getMessage()
                                        .reply("There's been an error\nCommand either does not" +
                                                " exist or I couldn't understand you").queue();
                            }

                            if (haveUsersStoppedTalking(talkingMembersCount)) isAllowedToCarryOn = false;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }




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
         * if list contains true (meaning that user has spoken recently) then false will be returned becase users have not stopped talking
         *
         * @param list of talking members {@link #talkingMembersCount} when user speaks → true is added to the {@link #talkingMembersCount}; else false
         * @return true if last {@link #MAX_VALUE} (100 as default) is 0 <br>
         * else false
         * @author krystof-cejchan
         */
        private boolean haveUsersStoppedTalking(ArrayList<Boolean> list) {

            try {
                for (int y = list.size() - MAX_VALUE; y < list.size(); y++) {
                    if (list.get(y)) return false;
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
            return true;

        }

    }


}

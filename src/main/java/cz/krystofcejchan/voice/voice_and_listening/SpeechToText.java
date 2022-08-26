package cz.krystofcejchan.voice.voice_and_listening;

import cz.krystofcejchan.commands.commands_voice.IListeningCommands;
import cz.krystofcejchan.commands.commands_voice.ListeningCommandManager;
import cz.krystofcejchan.link_to_externalfiles.ExternalFileNamesE;
import cz.krystofcejchan.link_to_externalfiles.InputStreamHolder;
import cz.krystofcejchan.main.Main;
import cz.krystofcejchan.objects.CurrentTextChannel;
import cz.krystofcejchan.objects.MemeberWhoTriggeredEchoCommand;
import cz.krystofcejchan.objects.MessageReceivedEvent_CustomClass;
import cz.krystofcejchan.objects.sound_files.SoundFile;
import cz.krystofcejchan.utility_class.GlobalValues;
import cz.krystofcejchan.utility_class.UtilityClass;
import cz.krystofcejchan.voice.PythonASCIIDecoding;
import net.dv8tion.jda.api.audio.AudioReceiveHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.audio.CombinedAudio;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;

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
    private static final List<byte[]> receivedBytes = new ArrayList<>();
    public static MessageReceivedEvent_CustomClass msgEvent;
    public static String channelId;
    static Guild guild;
    private static String text = "";

    /**
     * creates a new wav audio file and fills it with audio from the user represented in bytes
     *
     * @param outFile     {@link File} that will hold the decodedData
     * @param decodedData user's voice input in bytes
     * @throws IOException            no such file
     */
    private static void getWavFile(File outFile, byte[] decodedData) throws IOException {
        /*
         * float        sampleRate          = 16000;    // 8000,11025,16000,22050,44100,48000
         * int          sampleSizeInBits    = 16;       // 8,16
         * int          channels            = 1;        // 1,2
         */
        AudioFormat format = new AudioFormat(48000f, 16, 2, true, true);

        AudioSystem.write(new AudioInputStream(new ByteArrayInputStream(decodedData), format, decodedData.length),
                AudioFileFormat.Type.WAVE, outFile);

    }


    public static String getText() {
        return text;
    }

    public static void setText(String txt) {
        text = txt;
    }

    public void onEchoCommand(MessageReceivedEvent event) {
        MemeberWhoTriggeredEchoCommand.setMember(event.getMember());
        Member member = event.getMember();
        msgEvent = new MessageReceivedEvent_CustomClass(event);
        EchoHandler echoH = new EchoHandler();
        echoH.isAllowedToCarryOn = true;
        receivedBytes.clear();
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
               if(audioManager.isSelfMuted())
                   System.out.println("jooooooooooo");

                event.getChannel().sendMessage("I'm listening ...").queue();
            }

        } else {
            event.getChannel().sendMessage("You are nowhere to be found *sad noises*").queue();
        }
    }

    public void onEchoSlashCommand(@NotNull SlashCommandInteractionEvent event) throws NullPointerException, RateLimitedException {
        MemeberWhoTriggeredEchoCommand.setMember(event.getMember());
        Member member = event.getMember();
        msgEvent = new MessageReceivedEvent_CustomClass(new MessageReceivedEvent(Main.publicJDA, 1,
                event.getMessageChannel().retrieveMessageById(event.getMessageChannel().getLatestMessageId()).complete()));
        EchoHandler echoH = new EchoHandler();
        echoH.isAllowedToCarryOn = true;
        receivedBytes.clear();
        channelId = event.getChannel().getId();
        assert member != null;
        GuildVoiceState voiceState = member.getVoiceState();
        assert voiceState != null;
        AudioChannel channel = voiceState.getChannel();
        AudioChannel connectedChannelSelf = Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getSelfMember()
                .getVoiceState()).getChannel();
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
                event.reply("I'm listening ...").queue();
            }

        } else {
            event.getChannel().sendMessage("You are nowhere to be found *sad noises*").queue();
        }
    }

    public String getTranscription() {
        try {
            assert InputStreamHolder.fileNameToPathMap != null;
            if (PythonASCIIDecoding.decodeASCIItext(UtilityClass.runPyScript(InputStreamHolder.fileNameToPathMap.get(ExternalFileNamesE.SOUNDFILETOTEXT).toString(),
                    SoundFile.getWholePath() + " " + Language.lang, false)) == null)
                return null;
            String rawString = PythonASCIIDecoding.decodeASCIItext(UtilityClass.runPyScript(InputStreamHolder.fileNameToPathMap.get(ExternalFileNamesE.SOUNDFILETOTEXT).toString(),
                    SoundFile.getWholePath() + " " + Language.lang, false));
            assert rawString != null;
            byte[] bytes = rawString.getBytes(StandardCharsets.UTF_8);
            File soundFile = new File(SoundFile.getWholePath());

            System.out.println(soundFile.delete()
                    ? "Sound file deleted" : "Sound file failed to be deleted!!!");

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

        final int MAX_VALUE = GlobalValues.MAX_VALUE;
        final int MAX_AUDIO_RECORDING_DURATION = GlobalValues.MAX_AUDIO_RECORDING_DURATION;
        private final Queue<byte[]> queue = new ConcurrentLinkedQueue<>();
        public boolean isAllowedToCarryOn = true;
        ArrayList<Boolean> talkingMembersCount = new ArrayList<>();


        public boolean canReceiveCombined() {
            return queue.size() < 10;
        }

        @Override
        public void handleCombinedAudio(@NotNull CombinedAudio combinedAudio) {
            if (isAllowedToCarryOn) {
                guild.getAudioManager();

                receivedBytes.add(combinedAudio.getAudioData(1.12f));// 1.0 → 100%

                if (combinedAudio.getUsers().contains(MemeberWhoTriggeredEchoCommand.getMember().getUser()))
                    talkingMembersCount.add(true);

                else talkingMembersCount.add(false);

                if (talkingMembersCount.size() > MAX_VALUE) {
                    if (isAllowedToCarryOn &&
                            (haveUsersStoppedTalking(talkingMembersCount) || hasAudioRecordingDurationBeenExceeded())) {
                        try {
                            System.out.println(combinedAudio.getUsers().size());
                            int size = 0;
                            for (byte[] bs : receivedBytes) {
                                size += bs.length;
                            }
                            byte[] decodedData = new byte[size];
                            int i = 0;
                            for (byte[] bs : receivedBytes) {
                                for (byte b : bs) {
                                    decodedData[i++] = b;
                                }
                            }

                            SoundFile.setTitle(msgEvent.getEvent().getGuild().getId());

                            File file = new File(SoundFile.getWholePath());
                            System.out.println(SoundFile.getWholePath());

                            getWavFile(file, decodedData);
                            SpeechToText StT = new SpeechToText();
                            final String transcription_original = StT.getTranscription();
                            String transcription_finalVersion;

                            Objects.requireNonNull(guild.getTextChannelById(CurrentTextChannel.getId()))
                                    .sendMessage(transcription_original).queue();
                            if (!((SpeechToText.Language.getLang().equals("en-GB") || SpeechToText.Language.getLang()
                                    .equals("en-US")))) {
                                assert InputStreamHolder.fileNameToPathMap != null;
                                transcription_finalVersion = UtilityClass.runPyScript(InputStreamHolder.fileNameToPathMap.get(ExternalFileNamesE.TRANSLATOR).toString(),
                                        transcription_original, false);
                                assert transcription_finalVersion != null;
                                Objects.requireNonNull(guild.getTextChannelById(CurrentTextChannel.getId()))
                                        .sendMessage(transcription_finalVersion).queue();
                            } else
                                transcription_finalVersion = transcription_original;


                            System.out.println(transcription_finalVersion);

                            SpeechToText.setText(transcription_finalVersion);


                            if (new ListeningCommandManager().getCommand(transcription_finalVersion) != null) {
                                IListeningCommands command = new ListeningCommandManager().getCommand(transcription_finalVersion);
                                assert command != null;
                                if (command.isParamRequired()) {
                                    if (!transcription_original.isEmpty() && !transcription_original.isBlank())
                                        command.doTask(msgEvent.getEvent(), transcription_original);

                                } else command.doTask(msgEvent.getEvent(), null);
                            } else {
                                msgEvent.getEvent().getMessage()
                                        .reply("There's been an error\nCommand either does not" +
                                                " exist or I couldn't understand you").queue();
                            }

                            if (haveUsersStoppedTalking(talkingMembersCount))
                                isAllowedToCarryOn = false;
                            if (hasAudioRecordingDurationBeenExceeded()) {
                                isAllowedToCarryOn = false;
                                msgEvent.getEvent().getChannel().sendMessage("Speech duration has been exceeded")
                                        .queue();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        @Override
        public boolean includeUserInCombinedAudio(User user) {
            return user.equals(MemeberWhoTriggeredEchoCommand.getMember().getUser());
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

        private boolean hasAudioRecordingDurationBeenExceeded() {
            //    System.out.println(receivedBytes.size());
            return receivedBytes.size() >= MAX_AUDIO_RECORDING_DURATION;
        }

    }


}
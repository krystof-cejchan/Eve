package audioplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.ByteBuffer;

/**
 * Lava Player set up
 */
public class AudioPlayerSendHandler implements AudioSendHandler {

    private final AudioPlayer AUDIOPLAYER;
    private final ByteBuffer BUFFER;
    private final MutableAudioFrame FRAME;

    public AudioPlayerSendHandler(AudioPlayer audioPlayer) {
        this.AUDIOPLAYER = audioPlayer;
        this.BUFFER = ByteBuffer.allocate(1024);
        this.FRAME = new MutableAudioFrame();
        this.FRAME.setBuffer(BUFFER);
    }

    public boolean canProvide() {
        return this.AUDIOPLAYER.provide(FRAME);
    }

    @Override
    public ByteBuffer provide20MsAudio() {

        return BUFFER.flip();
    }

    @Override
    public boolean isOpus() {

        return true;
    }

}

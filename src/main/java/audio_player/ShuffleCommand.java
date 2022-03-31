package audio_player;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ShuffleCommand {

    public void getShuffle(MessageReceivedEvent event) {

        final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());

        if (musicManager.SCHEDULER.QUEUE.isEmpty()) {
            event.getChannel().sendMessage("The queue is currently empty!").queue();
            return;
        }

        musicManager.SCHEDULER.shuffle();
        event.getChannel().sendMessage("The queue has been shuffled!").queue();
    }

}

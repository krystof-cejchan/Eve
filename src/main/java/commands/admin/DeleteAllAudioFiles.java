package commands.admin;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import objects.sound_files.DeleteSoundAudioFilesFromSystemAndDatabase;

import java.sql.SQLException;

/**
 * deletes all audio files that may be left undeleted → bot failed to delete them
 */
public class DeleteAllAudioFiles implements IAdmin {
    /**
     * deletes all sound files from paths stored in database
     * {@link DeleteSoundAudioFilesFromSystemAndDatabase}
     *
     * @param event {@link MessageReceivedEvent}
     */
    @Override
    public void commitAdminOperation(MessageReceivedEvent event) {
        if (!IAdmin.isVerified(event))
            return;
        try {
            DeleteSoundAudioFilesFromSystemAndDatabase.deleteAllSoundFilesOnebyOneFromSystemAndDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getTags() {
        return new String[]{"DELETEALLAUDIOFILES"};
    }
}

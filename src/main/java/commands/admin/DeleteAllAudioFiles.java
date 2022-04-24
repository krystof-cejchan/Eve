package commands.admin;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import objects.sound_files.DeleteSoundAudioFilesFromSystemAndDatabase;

import java.sql.SQLException;

public class DeleteAllAudioFiles implements IAdmin {
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

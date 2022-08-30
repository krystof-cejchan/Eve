package cz.krystofcejchan.main.after_startup;

import cz.krystofcejchan.main.Main;

public class DeleteCertainSlashCommands implements IAfterStartUp{
    @Override
    public void doAfterStartUp() {
        Main.publicJDA.getGuilds().forEach(guild->{
        //    guild.getC
                guild.deleteCommandById("").queue();
        });
    }
}

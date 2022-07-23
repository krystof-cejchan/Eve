package cz.krystofcejchan.main.after_startup;

import cz.krystofcejchan.database.sqlite.users_custom_playlists.connection.ConnectToDatabase;

public class DatabaseConnectionEstablishment implements IAfterStartUp {

    @Override
    public void doAfterStartUp() {
        ConnectToDatabase.getInstance().connectToDatabase();
    }
}

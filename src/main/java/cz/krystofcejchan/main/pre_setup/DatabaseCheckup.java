package cz.krystofcejchan.main.pre_setup;

import cz.krystofcejchan.database_SQLite.analytics.connection.ConnectToDatabase;
import cz.krystofcejchan.database_SQLite.analytics.creation.CreationOfDatabase;

import java.io.IOException;

public class DatabaseCheckup implements IPreSetUp{
    @Override
    public void GetReady() throws IOException {
       CreationOfDatabase creationOfDatabase =  new CreationOfDatabase();
       creationOfDatabase.checkIfDatabaseExistsAndCreateNewOneIfNeeded();
      System.out.println(new ConnectToDatabase().connectToDatabase());
    }
}

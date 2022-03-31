package main.onStart;

import java.io.IOException;

import db.Database;
import db.DbCommands;

public class Database_class_onStart implements IOnStart {

	@Override
	public void doYourPart() {

		try {
			Database db = new Database("H:\\SQLite\\eve_database.db");
			DbCommands db_cmds = new DbCommands(db.getPath());

			db.createNewDB_withWholePath();
			db.connectToDB();

			db_cmds.createTable(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

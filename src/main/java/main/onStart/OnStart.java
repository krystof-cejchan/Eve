package main.onStart;

import java.io.IOException;
import java.sql.SQLException;

import commands.CommandManager;
import commands.ICommands;
import db.Database;
import db.DbCommands;

public class OnStart {
	private int AverageTriggerCountFromCommands;

	public OnStart() {
		setAverageTriggerCountFromCommands(calcAverageTriggerCount());
	}

	public int getAverageTriggerCountFromCommands() {
		return AverageTriggerCountFromCommands;
	}

	public void setAverageTriggerCountFromCommands(int averageTriggerCountFromCommands) {
		AverageTriggerCountFromCommands = averageTriggerCountFromCommands;
	}

	public int calcAverageTriggerCount() {
		int helper = 0;
		for (ICommands iCommands : CommandManager.getAllCommands()) {
			helper += iCommands.getTriggers().size();
		}
		return helper / CommandManager.getAllCommands().size();
	}

	/**
	 * sets up new database if needed and connects to it
	 * 
	 * @throws
	 */
	private static void db_init() throws SQLException, IOException {

		Database db = new Database("H:\\SQLite\\eve_database.db");
		DbCommands db_cmds = new DbCommands(db.getPath());

		db.createNewDB_withWholePath();
		db.connectToDB();

		db_cmds.createTable(null);

	}

}

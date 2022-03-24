package db;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	String path;
	int guild;

	boolean isConnSuccesfully = false;

	public Database(String path, int guild) {
		// TODO Auto-generated constructor stub
		this.path = path;
		this.guild = guild;

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getGuild() {
		return guild;
	}

	public void setGuild(int guild) {
		this.guild = guild;
	}

	/**
	 * @param pass path to the db "C:/folder1/folder2/databaseFile.db"
	 * @see {@code createNewDB_withFileName(String fileName)}
	 */
	public void createNewDB_withWholePath(String path) {
		String url = "jdbc:sqlite:" + path;
		java.io.File db = new java.io.File(path);

		if (!db.exists()) {
			@SuppressWarnings("unused")
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url);
				isConnSuccesfully = true;
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param pass filename "database1.db"
	 * @see {@code createNewDB_withWholePath(String path)}
	 */
	public void createNewDB_withFileName(String fileName) {
		String url = "jdbc:sqlite:H:/SQLite/" + fileName;

		@SuppressWarnings("unused")
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
			isConnSuccesfully = true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public boolean isConnected() {
		return isConnSuccesfully;
	}

}

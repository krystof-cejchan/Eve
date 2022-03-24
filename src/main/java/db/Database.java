package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	String path;
	int guild;
	String fullPath;

	boolean isConnSuccesfully = false;

	public Database(String path, int guild) {
		// TODO Auto-generated constructor stub
		this.path = path;
		this.guild = guild;

		this.fullPath = "jdbc:sqlite:" + path;

	}

	public String getFullPath() {
		return fullPath;
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
	public void createNewDB_withWholePath() {

		java.io.File db = new java.io.File(getPath());

		if (!db.exists()) {
			@SuppressWarnings("unused")
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(fullPath);
				isConnSuccesfully = true;
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void connectToDB() {
		Connection conn = null;
		try {

			conn = DriverManager.getConnection(fullPath);

			System.out.println("Connection to SQLite has been established.");
			isConnSuccesfully = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
					isConnSuccesfully = false;
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	/**
	 * @param pass filename "database1.db"
	 * @see {@code createNewDB_withWholePath(String path)}
	 */
	public void createNewDB_withFileName() {
		@SuppressWarnings("unused")
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(fullPath);
			isConnSuccesfully = true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void closeConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(fullPath);
		conn.close();
	}

	public boolean isConnected() throws SQLException {
		Connection conn = DriverManager.getConnection(fullPath);
		if (conn.isClosed())
			return false;

		return true;
	}

}

package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    String path;

    String fullPath;

    boolean isConnSuccesfully = false;

    public Database(String path) {

        this.path = path;

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

    /**
     * @throws IOException if files is not found
     * @see {@code createNewDB_withFileName(String fileName)}
     */
    public void createNewDB_withWholePath() throws IOException {

        java.io.File db = new java.io.File(getPath());
        db.createNewFile();

        if (!db.exists()) {
            @SuppressWarnings("unused")
            Connection conn = null;
            isConnSuccesfully = true;
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

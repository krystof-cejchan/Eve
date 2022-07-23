package database.sq_lite.users_custom_playlists.connection;

import org.junit.jupiter.api.Test;

import java.sql.*;

class ConnectToDatabaseTest {

    @Test
    void Public() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = (DriverManager.getConnection("jdbc:sqlite:D:\\USERS_INPUT_AUDIO\\PUBLIC_PLAYLISTS_DB.db"));
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(-1);
        }
      /*  connection.prepareStatement("CREATE TABLE Persons (\n" +
           "    PersonID int,\n" +
           "    LastName varchar(255),\n" +
           "    FirstName varchar(255),\n" +
           "    Address varchar(255),\n" +
           "    City varchar(255)\n" +
           ");").execute();*/

        String sql = """
                SELECT * FROM public_playlists
                ORDER BY popularity desc
                LIMIT 12;""";
        PreparedStatement prepared = connection.prepareStatement(sql);
        ResultSet resultSet = prepared.executeQuery();
        ResultSetMetaData metadata = resultSet.getMetaData();
        int columnCount = metadata.getColumnCount();
        while (resultSet.next()) {
            System.out.println("pause");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(resultSet.getString(i));
            }


        }
        System.out.println("---END---");
        resultSet.close();
        connection.close();
    }
}

package cz.krystofcejchan.database.sqlite.users_custom_playlists.commit_queries;

import cz.krystofcejchan.database.sqlite.users_custom_playlists.objects.records.PublicPlaylistsRecord;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Queries {

    public static boolean createDefaultTable(Connection connection) throws SQLException {
        String query = """
                CREATE TABLE IF NOT EXISTS "public_playlists" (
                \t"id"\tINTEGER NOT NULL UNIQUE,
                \t"title"\tTEXT NOT NULL,
                \t"desc"\tTEXT NOT NULL,
                \t"guild_id"\tINTEGER NOT NULL,
                \t"author"\tTEXT NOT NULL,
                \t"songs"\tTEXT NOT NULL,
                \t"popularity"\tINTEGER DEFAULT 0,
                \t"played_n"\tINTEGER DEFAULT 0,
                \tPRIMARY KEY("id" AUTOINCREMENT)
                );""";
        return connection.prepareStatement(query).execute();
    }

    public static void addRecord(Connection connection, PublicPlaylistsRecord record) throws SQLException {

//        INSERT INTO public_playlists (title, guild_id, author, songs)
//VALUES ('a', 'b', 'c', 'https://www.youtube.com/watch?v=QwVjTlTdIDQ;https://www.youtube.com/watch?v=QwVjTlTdIDQ');

        String query = "INSERT INTO public_playlists (title,desc, guild_id, author, songs)" +
                "VALUES ('" + record.getTitle() +
                "', '" + record.getDesc() +
                "', '" + record.getGuild_id() +
                "', '" + record.getAuthor() + "', " + "'" +
                record.getSongs() +
                "');";
        connection.prepareStatement(query).execute();
    }

    public static class Retrieve {
        public static ResultSet recordByTitle(Connection connection, String title, boolean fromThisGuild,
                                              Long guildId) throws SQLException {

            return connection.prepareStatement(!fromThisGuild
                            ? "SELECT * from public_playlists WHERE title like '%" + title + "%';"
                            : "SELECT * from public_playlists WHERE title like '%" + title + "%' AND guild_id='" + guildId + "';")
                    .executeQuery();
        }

        /**
         * 12 most popular records
         *
         * @param connection {@link Connection}
         * @return ResultSet
         * @throws SQLException if database connection fails
         */
        public static List<List<String>> mostPopularRecords(Connection connection) throws SQLException {
            List<List<String>> db2D = new ArrayList<>();
            String sql = """
                    SELECT * FROM public_playlists
                    ORDER BY popularity desc
                    LIMIT 12;""";
            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount(), rowCount = 0;
            while (resultSet.next()) {
                List<String> rows = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    rows.add(resultSet.getString(i));
                }
                db2D.add(rowCount, rows);
                rowCount++;
            }
        //    System.out.println(hashMap.size());
            resultSet.close();

            return db2D;

        }
    }
}

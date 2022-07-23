package cz.krystofcejchan.database.sqlite.users_custom_playlists.backup.backedup_db

import cz.krystofcejchan.database.sqlite.users_custom_playlists.connection.ConnectToDatabase
import java.sql.SQLException
import java.util.*

object BackUpDB {
    @Throws(SQLException::class)
    fun backUpTo(path: String): Boolean {
        val sql = """
            BACKUP DATABASE testDB
            TO DISK = '$path';
            """.trimIndent()
        return Objects.requireNonNull(ConnectToDatabase.getInstance().connectToDatabase()).prepareStatement(sql)
            .execute()
    }
}
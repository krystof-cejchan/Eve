package cz.krystofcejchan.commands.commands_slash.music.users_custom_playlists.logics;

public record DatabaseData(String id,
                           String title,
                           String desc,
                           String guild_id,
                           String author,
                           String songs,
                           String creation_date,
                           String popularity,
                           String played_n) {
}

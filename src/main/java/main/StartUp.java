package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import db.Database;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class StartUp {
	static JDABuilder jda;

	public static void main(String[] args) throws LoginException, SQLException, IOException {
		EnumSet<GatewayIntent> intents = EnumSet.of(

				GatewayIntent.GUILD_MESSAGES,

				GatewayIntent.GUILD_VOICE_STATES);

		@SuppressWarnings("unused")
		String APITokenMAIN, APITokenTEST;
		APITokenMAIN = "OTMzNDgyMTE1MDM3ODEwNzI5.YeiK9w.Qy3vSVFx5zQ8MsjL0jHVbLmrMXs";
		APITokenTEST = "OTM2Njc1NTQzNzU0MDg4NTQw.YfQpFA.sVKCrcPurel5KqKyLC-5lk0eS5M";

		jda = JDABuilder.createDefault(APITokenTEST, intents);
		jda.setActivity(Activity.competing("Hey Eve"));
		jda.enableCache(CacheFlag.VOICE_STATE);
		jda.setStatus(OnlineStatus.ONLINE);
		jda.addEventListeners(new Listener());
		jda.build();

		db_init();

	}

	/**
	 * sets up new database if needed and connects to it
	 * 
	 * @throws IOException
	 */
	private static void db_init() throws SQLException, IOException {

		Database db = new Database("H:\\SQLite\\eve_database.db");
		db.createNewDB_withWholePath();
		db.connectToDB();

	}

}

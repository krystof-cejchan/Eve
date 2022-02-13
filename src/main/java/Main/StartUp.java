package Main;

import java.util.EnumSet;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class StartUp {
	static JDABuilder jda;

	public static void main(String[] args) throws LoginException {
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

	}

	public String getBotsId() {
		return jda.toString();

	}

}

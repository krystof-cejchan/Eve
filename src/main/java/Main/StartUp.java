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

		String APITokenMAIN, APITokenTEST;
		APITokenMAIN = "OTMzNDgyMTE1MDM3ODEwNzI5.YeiK9w.k3jvmDgGu5Qgk_19dVk7d8CY9IY";
		APITokenTEST = "OTM3MTA0ODE5MTY3MDY4MjMy.YfW43w.0kDl03FxJvgxyJ8a2tBDYDbwkLk";

		jda = JDABuilder.createDefault(APITokenTEST, intents);
		jda.setActivity(Activity.competing("'help"));
		jda.enableCache(CacheFlag.VOICE_STATE);
		jda.setStatus(OnlineStatus.ONLINE);
		jda.addEventListeners(new Commands());
		jda.build();

	}

	public String getBotsId() {
		return jda.toString();

	}

}

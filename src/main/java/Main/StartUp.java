package Main;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class StartUp {
	static JDABuilder jda;

	public static void main(String[] args) throws LoginException {

		jda = JDABuilder.createDefault("OTMzNDgyMTE1MDM3ODEwNzI5.YeiK9w.k3jvmDgGu5Qgk_19dVk7d8CY9IY");
		//jda = JDABuilder.createDefault("OTM3MTA0ODE5MTY3MDY4MjMy.YfW43w.0kDl03FxJvgxyJ8a2tBDYDbwkLk");
		jda.setActivity(Activity.competing("'help"));
		
		jda.setStatus(OnlineStatus.ONLINE);
		jda.addEventListeners(new Commands());
		jda.build();

	}

	public String getBotsId() {
		return jda.toString();

	}

}

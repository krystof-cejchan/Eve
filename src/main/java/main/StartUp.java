package main;

import _library_class.LibraryClass;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.EnumSet;

public class StartUp {
    static JDABuilder jda;

    public static void main(String[] args) throws Exception {
//LibraryClass.givenPythonInterpreter_whenPrintExecuted_thenOutputDisplayed();
        EnumSet<GatewayIntent> intents = EnumSet.of(

                GatewayIntent.GUILD_MESSAGES,

                GatewayIntent.GUILD_VOICE_STATES);

        @SuppressWarnings("unused")
        String APITokenTEST = "OTM2Njc1NTQzNzU0MDg4NTQw.YfQpFA.sVKCrcPurel5KqKyLC-5lk0eS5M";
        @SuppressWarnings("unused")
        String APITokenMAIN = "OTMzNDgyMTE1MDM3ODEwNzI5.YeiK9w.Qy3vSVFx5zQ8MsjL0jHVbLmrMXs";


        jda = JDABuilder.createDefault(APITokenTEST, intents);
        jda.setActivity(Activity.competing("Hey Eve-ing in " + /*(jda.build().getGuilds().size()) */"X" + " servers"));
        jda.enableCache(CacheFlag.VOICE_STATE);
        jda.setStatus(OnlineStatus.ONLINE);
        jda.addEventListeners(new Listener());
        jda.build();

        /*
         * for (IOnStart iOnStart : new OnStartManager().getListOf_onStartClasses()) {
         * iOnStart.doYourPart(); }
         */

    }

}

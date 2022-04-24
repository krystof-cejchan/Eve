package main;

import main.onStart.OnStartManager;
import main.pre_SetUp.preSetUpManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import objects.SoundFiles.SoundFile;

import java.util.EnumSet;

public class StartUp {
    static JDABuilder jda;

    public static void main(String[] args) throws Exception {

        new preSetUpManager().getPreSetUps().forEach(setUp -> {
            try {
                setUp.GetReady();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
            System.out.println("All set up");
        });

        new OnStartManager().getListOf_onStartClasses().forEach(iClass -> {
            try {
                iClass.doYourPart();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
            System.out.println("All checked");
        });

        System.out.println(SoundFile.Directories.getTheFirstDiskLetter());

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


    }

}

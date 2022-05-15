package main;


import commands.commands_slash.SlashCommandListener;
import main.onStart.OnStartManager;
import main.onStart.addingSlashCommandsToGuilds;
import main.pre_SetUp.preSetUpManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import objects.sound_files.SoundFile;

import java.util.EnumSet;

public class StartUp {
    public static JDA publicJDA;

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

                GatewayIntent.GUILD_VOICE_STATES,

                GatewayIntent.GUILD_EMOJIS);


        @SuppressWarnings("unused")
        String APITokenTEST = "OTM2Njc1NTQzNzU0MDg4NTQw.YfQpFA.sVKCrcPurel5KqKyLC-5lk0eS5M";
        @SuppressWarnings("unused")
        String APITokenMAIN = "OTMzNDgyMTE1MDM3ODEwNzI5.YeiK9w.Qy3vSVFx5zQ8MsjL0jHVbLmrMXs";


        JDABuilder jda = JDABuilder.createDefault(APITokenTEST, intents);
        //jda.setActivity(Activity.competing("Hey!"));
        jda.setRawEventsEnabled(true);
        jda.enableCache(CacheFlag.VOICE_STATE);
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setActivity(Activity.playing(";hey!"));
        jda.addEventListeners(new Listener(), new SlashCommandListener());
        publicJDA = jda.build().awaitReady();
        //jda.build().awaitReady();


        addingSlashCommandsToGuilds.addSlashCommandsToTheGuilds();

    }


}

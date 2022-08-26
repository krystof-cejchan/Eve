package cz.krystofcejchan.main;


import cz.krystofcejchan.enums_annotations_exceptions.enums.OS;
import cz.krystofcejchan.listeners.*;
import cz.krystofcejchan.main.after_startup.AfterStartUpManager;
import cz.krystofcejchan.main.after_startup.IAfterStartUp;
import cz.krystofcejchan.main.onstart.OnStartManager;
import cz.krystofcejchan.main.pre_setup.preSetUpManager;
import cz.krystofcejchan.utility_class.GlobalValues;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;


/**
 * Main class
 */
public class Main {
    public static JDA publicJDA;

    /**
     * Main method
     * prepares and launches the bot and all needed services
     *
     * @param args params
     * @throws Exception general exception
     */
    public static synchronized void main(String[] args) throws Exception {
        //determinate the os
        GlobalValues.operatingSystem = System.getProperty("os.name").toLowerCase().contains("win") ? OS.WINDOWS : OS.LINUX;

        new preSetUpManager().getPreSetUps().forEach(setUp -> {
            try {
                setUp.getReady();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
            System.out.println("All set up");
        });

        new OnStartManager().getListOf_onStartClasses().forEach(onStart -> {
            try {
                onStart.doYourPart();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
            System.out.println("All checked");
        });

        java.util.EnumSet<GatewayIntent> intents = java.util.EnumSet.of(

                GatewayIntent.GUILD_MESSAGES,

                GatewayIntent.GUILD_VOICE_STATES,

                GatewayIntent.GUILD_EMOJIS);

        Object[] allActiveListeners = {
                new Listener(),
                new SlashCommandListener(),
                new AutocompleteListener(),
                new FirstJoinServerListener(),
                new DropDownListListener(),
                new ButtonClickListener()
        };

        JDABuilder jda = JDABuilder.createDefault(args[0], intents);

        jda.setRawEventsEnabled(true);
        jda.enableCache(CacheFlag.VOICE_STATE);
        jda.setStatus(OnlineStatus.ONLINE);
        jda.setActivity(Activity.watching("\uD835\uDDE8\uD835\uDE04\uD835\uDDE8"));
        jda.addEventListeners(allActiveListeners);
        publicJDA = jda.build().awaitReady();

        try {
            new AfterStartUpManager().
                    getIAfterStartUpArrayList().forEach(IAfterStartUp::doAfterStartUp);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}

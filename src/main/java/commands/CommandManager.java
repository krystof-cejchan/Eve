package commands;

import commands.textCommands.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class CommandManager {

    public final static ArrayList<ICommands> commands = new ArrayList<>();

    public CommandManager() {
        addNewCommand(new Help());
        addNewCommand(new Prefix());
        addNewCommand(new EmotionalDamage());
        addNewCommand(new Jesus());
        addNewCommand(new Gif());
        addNewCommand(new Pp());
        addNewCommand(new SaySomethingNice());
        addNewCommand(new ServerInfo());
        addNewCommand(new Support());
        addNewCommand(new Me());
        addNewCommand(new Join());
        addNewCommand(new Leave());
        addNewCommand(new Play());
        addNewCommand(new PlayQueue());
        addNewCommand(new Queue());
        addNewCommand(new SkipOne());
        addNewCommand(new SkipTo());
        addNewCommand(new Ff());
        addNewCommand(new Remove());
        addNewCommand(new NowPlaying());
        addNewCommand(new Shuffle());
        addNewCommand(new Pause());
        addNewCommand(new Resume());
        addNewCommand(new Stop());
        addNewCommand(new Volume());
        addNewCommand(new Mute());
        addNewCommand(new Unmute());
        addNewCommand(new EchoText());
        addNewCommand(new SupportedLanguages());
        addNewCommand(new Memes());
        addNewCommand(new UploadSlash());
        /*
         * CommandManager.getClassesFromPackage("commands").forEach((CLASS_var) -> { try
         * { if (CLASS_var.getSimpleName().toString().startsWith("_"))
         * addNewCommand(CLASS_var.getDeclaredConstructor().newInstance());
         *
         *
         * } catch (InstantiationException | IllegalAccessException |
         * IllegalArgumentException | InvocationTargetException | NoSuchMethodException
         * | SecurityException multiExep) { System.out.println(multiExep); } });
         */

    }

    /*
     * public static Set<Class<?>> getClassesFromPackage(String packageName) throws
     * IOException { return
     * ClassPath.from(ClassLoader.getSystemClassLoader()).getAllClasses().stream()
     * .filter(classVar -> classVar.getPackageName().equalsIgnoreCase(packageName))
     * .map(classVar -> classVar.load()).collect(Collectors.toSet()); }
     */

    private void addNewCommand(ICommands Icmd) {
        if (commands.stream().noneMatch(match -> match.getName().equals(Icmd.getName()))) {
            commands.add(Icmd);
        }
    }

    public static ArrayList<ICommands> getAllCommands() {
        return commands;
    }

    public static ICommands getCommandbyId(int id) {
        return commands.get(id);
    }

    @Nullable
    public ICommands getCommand(MessageReceivedEvent event) {

        try {
            String[] args = event.getMessage().getContentRaw().split(" ");

            if (args[0].startsWith(main.Prefix.getValue())) {

                for (ICommands iCommands : commands) {

                    for (int i = 0; i < iCommands.getTriggers().size(); i++) {

                        for (int j = 0; j < commands.size(); j++) {

                            if (iCommands.getTriggers().get(i).equals(args[0].substring(1).toLowerCase())) {

                                return iCommands;
                            }
                        }
                    }
                }
            }
            return null;

        } catch (

                Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

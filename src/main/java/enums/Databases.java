package enums;

public enum Databases {
    COMMANDS_ANALYTICS, SOUNDFILES;


    Databases[] getAll() {
        return Databases.values();
    }
}

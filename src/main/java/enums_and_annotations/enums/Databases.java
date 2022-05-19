package enums_and_annotations.enums;

public enum Databases {
    COMMANDS_ANALYTICS, SOUNDFILES;


    Databases[] getAll() {
        return Databases.values();
    }
}

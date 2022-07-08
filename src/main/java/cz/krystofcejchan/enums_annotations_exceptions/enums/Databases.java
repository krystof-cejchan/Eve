package cz.krystofcejchan.enums_annotations_exceptions.enums;

public enum Databases {
    COMMANDS_ANALYTICS, SOUNDFILES;


    Databases[] getAll() {
        return Databases.values();
    }
}

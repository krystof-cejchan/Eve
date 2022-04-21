import db.Database;
import db.DbCommands;
import main.onStart.IOnStart;
import objects.ScriptPathPointer;

import java.io.IOException;

public class Database_class_onStart implements IOnStart {

    @Override
    public void doYourPart() {

        try {
            Database db = new Database(ScriptPathPointer.dbPath);
            DbCommands db_cmds = new DbCommands(db.getPath());

            db.createNewDB_withWholePath();
            db.connectToDB();

            db_cmds.createTable(null);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}

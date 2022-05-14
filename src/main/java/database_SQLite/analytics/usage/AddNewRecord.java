package database_SQLite.analytics.usage;


import database_SQLite.analytics.queries.InsertValuesToTable;
import enums.MessageType_VOICE_TEXT;
import main.Prefix;
import net.dv8tion.jda.api.entities.Message;
import org.sqlite.SQLiteException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import static _library_class.LibraryClass.getCurrentDate;

public class AddNewRecord extends InsertValuesToTable {
    public void addNewRecordToDatabase_onNewCommandReceived(String commandName, Message msg,
                                                            MessageType_VOICE_TEXT voice_text) throws SQLException {
        boolean moreThanOne = false;
        try {

            if (msg.getContentRaw().split(" ").length > 1) {
                moreThanOne = true;
                insertValuesToTable(null, "'" + commandName + "'" + "," + "'" + voice_text.toString()
                        + "'" + "," + "'" + msg.getContentRaw()
                        .replace(Prefix.getValue(), "")
                        .substring(0, msg.getContentRaw().indexOf(" ")) + "'" + "," + "'" +
                        getCurrentDate(false) + "'" + "," + "'"
                        + msg.getGuild().getId() + " " + msg.getGuild().getName() + "';");
            } else {
                insertValuesToTable(null, "'" + commandName + "'" + "," + "'" + voice_text.toString()
                        + "'" + "," + "'" + msg.getContentRaw()
                        .replace(Prefix.getValue(), "") + "'" + "," + "'" +
                        getCurrentDate(false) + "'" + "," + "'"
                        + msg.getGuild().getId() + " " + msg.getGuild().getName() + "'");

            }

        } catch (SQLiteException sqlException) {
            sqlException.printStackTrace();

        } finally {
            connectToDatabase().close();
            try {
                if (moreThanOne) {
                    insertValuesToTable(null, "'" + commandName + "'" + "," + "'" + voice_text.toString()
                            + "'" + "," + "'" + msg.getContentRaw()
                            .replace(Prefix.getValue(), "")
                            .substring(0, msg.getContentRaw().indexOf(" ")) + "'" + "," + "'" +
                            getCurrentDate(false) + "'" + "," + "'"
                            + msg.getGuild().getId() + " " + msg.getGuild().getName() + "';");
                } else {
                    insertValuesToTable(null, "'" + commandName + "'" + "," + "'" + voice_text.toString()
                            + "'" + "," + "'" + msg.getContentRaw()
                            .replace(Prefix.getValue(), "") + "'" + "," + "'" +
                            getCurrentDate(false) + "'" + "," + "'"
                            + msg.getGuild().getId() + " " + msg.getGuild().getName() + "'");

                }
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
      /*  getAllColumns().forEach(column -> {
            try {
                switch (column) {
                    case "command_name" -> insertValuesToTable(null, column, commandName);
                    case "command_type" -> insertValuesToTable(null, column, voice_text.toString());
                    case "command_specification" -> insertValuesToTable(null, column, msg.getContentRaw()
                            .replace(Prefix.getValue(), "").substring(0, msg.getContentRaw().indexOf(" ")));
                    case "time" -> insertValuesToTable(null, column, getCurrentDate(false));
                    case "guild_from_id" -> insertValuesToTable(null, column,
                            msg.getGuild().getId() + " " + msg.getGuild().getName());
                    default -> System.out.println("database column not found");
                }
            } catch (SQLException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }

        });*/

    }

    public ArrayList<String> getAllColumns() throws SQLException {
        ArrayList<String> columns = new ArrayList<>();
        ResultSet rs = super.connectToDatabase().createStatement().executeQuery("SELECT * FROM commands;");
        ResultSetMetaData mrs = rs.getMetaData();
        for (int c = 1; c <= mrs.getColumnCount(); c++) {
            columns.add(mrs.getColumnLabel(c));
        }
        return columns;
    }
}

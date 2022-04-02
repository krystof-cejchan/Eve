package commands.commands_others;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.OffsetDateTime;

/**
 * Birthday class
 */
public class Birthday {
    /**
     * transform date to String
     *
     * @param date {@link OffsetDateTime}
     * @return OffsetDateTime → String date
     */
    public static String getNormalDate(OffsetDateTime date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        String dayS = String.valueOf(day);
        String monthS = String.valueOf(getMonthName(month));
        String yearS = String.valueOf(year);

        return dayS + " " + monthS + " " + yearS;
    }

    /**
     * transform time to String
     *
     * @param date {@link OffsetDateTime}
     * @return OffsetDateTime → String time
     */
    public static String getNormalTime(OffsetDateTime date) {
        int hour = date.getHour();
        int minute = date.getMinute();
        int second = date.getSecond();
        String hourS = String.valueOf(hour);
        String minuteS = String.valueOf(minute);
        String secondS = String.valueOf(second);

        return hourS + ":" + minuteS + ":" + secondS;
    }

    /**
     * returns month name by its index in the year
     *
     * @param monthIndex 1-12 representing months
     * @return name of a month
     */
    public static String getMonthName(int monthIndex) {
        return switch (monthIndex) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "N/A";
        };
    }

    /**
     * get date when {@link net.dv8tion.jda.api.entities.Guild} was created
     *
     * @param event used to get information about Guild
     * @return text value of the date when Guild was created
     */
    public String getBirthdayDate(MessageReceivedEvent event) {

        OffsetDateTime date;
        date = event.getGuild().getTimeCreated();
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        String dayS = String.valueOf(day);
        String monthS = String.valueOf(getMonthName(month));
        String yearS = String.valueOf(year);

        return dayS + " " + monthS + " " + yearS;
    }

    /**
     * get time when {@link net.dv8tion.jda.api.entities.Guild} was created
     *
     * @param event used to get information about Guild
     * @return text value of the time when Guild was created
     */
    public String getBirthdayTime(MessageReceivedEvent event) {

        OffsetDateTime date;
        date = event.getGuild().getTimeCreated();

        int hour = date.getHour();
        int minute = date.getMinute();
        int second = date.getSecond();
        String hourS = String.valueOf(hour);
        String minuteS = String.valueOf(minute);
        String secondS = String.valueOf(second);

        return hourS + ":" + minuteS + ":" + secondS;
    }
}

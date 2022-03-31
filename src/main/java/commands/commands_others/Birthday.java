package commands.commands_others;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.OffsetDateTime;

public class Birthday {
    public static String getNormalDate(OffsetDateTime date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        String dayS = String.valueOf(day);
        String monthS = String.valueOf(getMonthName(month));
        String yearS = String.valueOf(year);

        return dayS + " " + monthS + " " + yearS;
    }

    public static String getNormalTime(OffsetDateTime date) {
        int hour = date.getHour();
        int minute = date.getMinute();
        int second = date.getSecond();
        String hourS = String.valueOf(hour);
        String minuteS = String.valueOf(minute);
        String secondS = String.valueOf(second);

        return hourS + ":" + minuteS + ":" + secondS;
    }

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

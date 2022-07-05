package utility_class;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class Date {


    public static String getNormalDateAndTime(OffsetDateTime date, boolean toGTM0) {
        return getNormalDate(date, toGTM0) + " " + getNormalTime(date, toGTM0);
    }

    public static OffsetDateTime currentDateTime(boolean toGTM0) {
        if (toGTM0) return OffsetDateTime.now(ZoneId.of("Etc/GMT0"));

        return OffsetDateTime.now();
    }

    public static String getNormalDate(OffsetDateTime date, boolean toGTM0) {
        if (toGTM0) date.atZoneSameInstant(ZoneId.of("Etc/GMT0"));
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
    public static String getNormalTime(OffsetDateTime date, boolean toGTM0) {
        if (toGTM0) date.atZoneSameInstant(ZoneId.of("Etc/GMT0"));
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
}

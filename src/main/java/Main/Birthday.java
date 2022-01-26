package Main;

import java.time.OffsetDateTime;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Birthday {
public String getBirthdayDate(MessageReceivedEvent event)
	{
	
	OffsetDateTime date;
	date = event.getGuild().getTimeCreated();
	int day = date.getDayOfMonth();
	int month = date.getMonthValue();
	int year = date.getYear();
	 String dayS = String.valueOf(day);
	 String monthS = String.valueOf(getMonthName(month));
	 String yearS = String.valueOf(year);
	 
	
	
	
	return dayS+" "+monthS+" "+yearS;
	}


public String getBirthdayTime(MessageReceivedEvent event)
{

OffsetDateTime date;
date = event.getGuild().getTimeCreated();

int hour = date.getHour();
int minute = date.getMinute();
int second = date.getSecond();
 String hourS = String.valueOf(hour);
 String minuteS = String.valueOf(minute);
 String secondS = String.valueOf(second);



return hourS+":"+minuteS+":"+secondS;
}

protected String getMonthName(int monthIndex) {
	String mesic="";
	switch(monthIndex) {
	case 1: mesic="January"; break;
	case 2: mesic="February"; break;
	case 3: mesic="March"; break;
	case 4: mesic="April"; break;
	case 5: mesic="May"; break;
	case 6: mesic="June"; break;
	case 7: mesic="July"; break;
	case 8: mesic="August"; break;
	case 9: mesic="September"; break;
	case 10: mesic="October"; break;
	case 11: mesic="November"; break;
	case 12: mesic="December"; break;
	
	default: mesic="N/A";
	
	
	
	}
	return mesic;
}
}

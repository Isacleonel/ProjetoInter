package util;


import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class Convert {


	public static java.sql.Date toDate(LocalDate data) {
        return Date.valueOf(data);
    }

    public static LocalDate toLocalDate(java.sql.Date data) {
        return data.toLocalDate();
    }
    
    public static java.sql.Time toTime(LocalTime time){
    	return Time.valueOf(time);
    }
    
    public static LocalTime toLocalTime(java.sql.Time time){
    	return time.toLocalTime();
    }

    public static java.util.Date toUtilDate(LocalDate data){
    	return Date.from(data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate fromUtilDate(java.util.Date data){
    	return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static DayOfWeek toDayOfWeek(byte day){
    	switch(day){
    		case 1: return DayOfWeek.MONDAY;
    		case 2: return DayOfWeek.TUESDAY;
    		case 3: return DayOfWeek.WEDNESDAY;
    		case 4: return DayOfWeek.THURSDAY;
    		case 5: return DayOfWeek.FRIDAY;
    		case 6: return DayOfWeek.SATURDAY;
    		case 7: return DayOfWeek.SUNDAY;
    		default: return null;
    	}
    }
}
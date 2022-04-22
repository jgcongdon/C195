package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFiles {
       public static Calendar stringToCalendar(String strDate) throws ParseException{
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       Date date = sdf. parse(strDate);
       Calendar calendar=Calendar.getInstance();
       calendar.setTime(date);
       return calendar;
   } 
}
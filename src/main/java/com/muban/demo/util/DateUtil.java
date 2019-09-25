package com.muban.demo.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public static String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static String STANDARD_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static long string2Long(String time) throws ParseException {
		SimpleDateFormat default_format = new SimpleDateFormat(STANDARD_DATE_TIME_PATTERN);
		StringBuilder sb = new StringBuilder(time);
		//sb.deleteCharAt(time.lastIndexOf(':'));
		Date date = default_format.parse(sb.toString());
		return date.getTime();
	}

	public static long String2Long(String time, String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		StringBuilder sb = new StringBuilder(time);
		Date date = format.parse(sb.toString());
		return date.getTime();
	}

	public static String long2String(long time) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_PATTERN);
		StringBuilder date = new StringBuilder(format.format(new Date(time)));
		date.insert(date.length() - 2, ':');
		return date.toString();
	}

	public static long standStr2Long(String time) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
		StringBuilder sb = new StringBuilder(time);
		Date date = format.parse(sb.toString());
		return date.getTime();
	}

	public static long standStr2Long(String time,String f) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(f);
		StringBuilder sb = new StringBuilder(time);
		Date date = format.parse(sb.toString());
		return date.getTime();
	}

	public static String long2StandStr(long time) {
		SimpleDateFormat format = new SimpleDateFormat(STANDARD_DATE_TIME_PATTERN);
		StringBuilder date = new StringBuilder(format.format(new Date(time)));
		return date.toString();
	}
	
	public static String long2StandStr(String dateFormat , long time) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		StringBuilder date = new StringBuilder(format.format(new Date(time)));
		return date.toString();
	}

	public static String getFullTime(long time) {
		return DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getFormatTime(long time,String format) {
		return DateFormatUtils.format(time, format);
	}
	
	public static Date getCST(String strGMT) throws ParseException {
		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		return df.parse(strGMT);
	}

	public static long getCSTLong(String strGMT) throws ParseException {
		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		Date d = df.parse(strGMT);
		return d.getTime();
	}
	
	public static long getGMTLong(String strGMT) throws ParseException {
		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		Date d = df.parse(strGMT);
		return d.getTime();
	}
	
	public static long getLong(String pattern, String strGMT) throws ParseException {
		DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
		Date d = df.parse(strGMT);
		return d.getTime();
	}

	public static String getGMT(long longCST) {
		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("GMT")); // modify Time Zone.

		return (df.format(new Date(longCST)));
	}

	public static String getGMT(Date dateCST) {
		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("GMT")); // modify Time Zone.
		return (df.format(dateCST));
	}

	public static boolean isSameMonth(long date1, long date2) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date1);
		int month1 = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		c.setTimeInMillis(date2);
		int month2 = c.get(Calendar.MONTH);
		int year2 = c.get(Calendar.YEAR);
		return (month1 == month2) && (year == year2);
	}

	public static boolean isYesterday(long time) {
		long now = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		int day = c.get(Calendar.DAY_OF_YEAR);
		int year = c.get(Calendar.YEAR);

		Calendar n = Calendar.getInstance();
		n.setTimeInMillis(now);
		int daynow = n.get(Calendar.DAY_OF_YEAR);
		int yearnow = n.get(Calendar.YEAR);
		if (yearnow == year && (daynow - day) == 1) {
			return true;
		}
		return false;
	}

	public static long getTodayBeginMSecond() {
		Calendar  cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND,00);
		return cal.getTimeInMillis();
	}

	public static long getAfterDaysTime(long time, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		cal.add(Calendar.DAY_OF_MONTH, days);
		
		return cal.getTimeInMillis();
	}
	
	
	public static boolean isSameDay(long time) {
		long now = System.currentTimeMillis();
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);

		Calendar n = Calendar.getInstance();
		n.setTimeInMillis(now);
		return DateUtils.isSameDay(c, n);
	}

	public static int getDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String s = format.format(new Date(System.currentTimeMillis()));
		return Integer.valueOf(s);
	}
	
	public static int[] getYearandMonth() {
		Calendar cal = Calendar.getInstance();
		int[] monthInfo = new int[2];
		monthInfo[0] = cal.get(Calendar.YEAR);
		monthInfo[1] = cal.get(Calendar.MONTH)+1;
		
		return monthInfo;
	}
	
	 // 获取当前天所在月份的天数  
    public static int getDaysofMonth(int year, int month){  
        int days = 0;
        switch (month) {
	        case 4:  
	        case 6:  
	        case 9:  
	        case 11:  
	            days = 30;  
	            break;  
	        case 2:  
	            if (isLeapYear(year)) {
	                days = 29;  
	            } else {  
	                days = 28;  
	            }  
	            break;  
	        default:
	            days =31;  
	            break;  
        }  
          
        return days;  
    }  
      
    // 判断是否是润年  
    public static boolean isLeapYear(int year){  
        return (year%4 == 0 && year%100 != 0) || (year%400 == 0);  
    }
    
    public static Date getMonthZeroTime(long time) {
        return getMonthZeroTime(time, 0);
    }

    public static Date getMonthZeroTime(long time, int monthOffset) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND,0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        
        c.add(Calendar.MONTH, monthOffset);
        return c.getTime();
    }
   
    public static int getDaysBetween(long from, long end){
    	long ei=end-from; 
    	return (int)(ei/(1000*60*60*24)); 
    }
    
    public static Date getMonday(int weekOffset) {
        return getMondayZeroTime(Calendar.getInstance(), weekOffset);
    }

    public static Date getMondayZeroTime(long time, int weekOffset) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        return getMondayZeroTime(c, weekOffset);
    }

    private static Date getMondayZeroTime(Calendar c, int weekOffset) {
        c.add(Calendar.DAY_OF_MONTH, -((5 + c.get(Calendar.DAY_OF_WEEK)) % 7) + 7 * weekOffset);
        return DateUtils.truncate(c.getTime(), Calendar.DAY_OF_MONTH);
    }
    
    /**
     * yyyyMMdd, of integer type
     */
    public static int getDay(long time, int field, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        if (offset != 0) {
            calendar.add(field, offset);
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        return year * 10000 + month * 100 + day;
    }

    /**
     * yyyyMMdd, of integer type
     */
    public static int getDay(long time) {
        return getDay(time, Calendar.DATE, 0);
    }

    public static int getDay(Date date, int field, int offset) {
        return getDay(date.getTime(), field, offset);
    }

    public static int getDay(Date date) {
        return getDay(date, Calendar.DATE, 0);
    }
    
    public static boolean checkMondayThree(){
    	Calendar calendar = Calendar.getInstance();
    	if(calendar.get(Calendar.DAY_OF_WEEK) == 2 && calendar.get(Calendar.HOUR_OF_DAY) < 3){
    		return true;
    	}
    	return false;
    }
    
    public static int getWeekDay(long time, int field, int offset){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		if (offset != 0) {
			calendar.add(field, offset);
		}
		boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		// 获取周几
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		// 若一周第一天为星期天，则-1
		if (isFirstSunday) {
			weekDay = weekDay - 1;
			if (weekDay == 0) {
				weekDay = 7;
			}
		}
		return weekDay;
    }
}

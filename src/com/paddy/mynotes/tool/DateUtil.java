package com.paddy.mynotes.tool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.util.Log;

public class DateUtil {
	private static final String TAG = "DateUtil";

	public static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_TIME = "HH:mm:ss";

	private static Calendar calendar;

	public static String getCurrentDateTime() {
		return formatDate(new Date(), FORMAT_DATE_TIME);
	}

	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			date = dateFormat.parse(format);
		} catch (Exception e) {
			Log.e(TAG, "Exception when parseDate dateStr[" + dateStr
					+ "] format[" + format + "]");
		}
		return date;
	}

	public static String formatDate(Date date, String format) {
		String result = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			result = dateFormat.format(date);
		} catch (Exception e) {
			Log.e(TAG, "Exception when formatDate date[" + date + "] format["
					+ format + "]");
		}
		return result;
	}

	public static int getYear(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	public static int getMonth(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static int getDay(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MINUTE);
	}

	public static int getSecond(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.SECOND);
	}
}

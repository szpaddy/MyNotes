package com.paddy.mynotes.tool;

import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;

public class DateUtil {
	private static final String TAG = "DateUtil";

	/**
	 * if the then is on the same day as now, it shows just the time and if it's
	 * a different day, it shows just the date.
	 * 
	 * @param context
	 * @param timeMillis
	 *            the date to format
	 * @return
	 */
	public static String formatSameDayTime(Context context, long timeMillis) {
		String inFormat = !DateUtils.isToday(timeMillis) ? "MM/dd" : "hh:mm";
		return DateFormat.format(inFormat, timeMillis).toString();
	}

	public static String getShowDate(Context context, long timeMillis) {
		return DateUtils.formatDateTime(context, timeMillis,
				DateUtils.FORMAT_SHOW_DATE);
	}

	public static String getShowTimeWeek(Context context, long timeMillis) {
		return DateUtils.formatDateTime(context, timeMillis,
				DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_WEEKDAY);
	}

}

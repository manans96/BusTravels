package com.manan.busservice.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Manan Sanghvi
 *
 */
public class DateUtils {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
	
	public static Date today() {
		return new Date();
	}
	
	public static String sdfToday() {
		return sdf.format(today());
	}
	
	public static String formattedDate(Date date) {
		return date != null ? sdf.format(date) : sdfToday();
	}

}

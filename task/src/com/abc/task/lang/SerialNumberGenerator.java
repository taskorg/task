package com.abc.task.lang;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SerialNumberGenerator {
	private static int count = 10000;
	private static Calendar lastTime = null;
	private static DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

	synchronized public static String generate() {
		Calendar curTime = Calendar.getInstance();
		curTime.clear(Calendar.MILLISECOND);
		curTime.set(curTime.get(Calendar.YEAR), curTime.get(Calendar.MONTH),
				curTime.get(Calendar.DATE), curTime.get(Calendar.HOUR_OF_DAY),
				curTime.get(Calendar.MINUTE), curTime.get(Calendar.SECOND));
		if (lastTime == null) {
			lastTime = curTime;
			// System.out.println(curTime.getTimeInMillis() + "_"+
			// lastTime.getTimeInMillis());
		}
		if (curTime.compareTo(lastTime) == 0) {
			count++;
		} else {
			// System.out.println(curTime.getTimeInMillis() + "_"
			// + lastTime.getTimeInMillis());

			count = 10001;
			lastTime = curTime;
		}
		return format.format(curTime.getTime()) + count;
	}
}

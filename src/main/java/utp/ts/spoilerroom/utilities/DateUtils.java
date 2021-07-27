package utp.ts.spoilerroom.utilities;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DateUtils
{
	private static final String DATE_LONG_FORMAT = "dd MMM yyyy, hh:mm a";
	private static final String DATE_SHORT_FORMAT = "MMM yyy";

	public boolean isOnline(Timestamp timestamp)
	{
		Timestamp now = new Timestamp(System.currentTimeMillis());

		long oldTime = timestamp.getTime();
		long currentTime = System.currentTimeMillis();

		long dif = (currentTime - oldTime) / (60 * 1000);
		
		return dif <= 15;
	}

	public String dateToLongFormat(Timestamp timestamp)
	{
		return dateToFormat(timestamp, DATE_LONG_FORMAT);
	}

	public String dateToShortFormat(Timestamp timestamp)
	{
		return dateToFormat(timestamp, DATE_SHORT_FORMAT);
	}

	private String dateToFormat(Timestamp timestamp, String format)
	{
		return timestamp.toLocalDateTime().format(DateTimeFormatter.ofPattern(format));
	}
}

package product.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * A utility for formatting the Date and Time.
 */
public class TimeUtilities {

	/**
	 * The Date format used in formatted Dates.
	 */
	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * The Date Time format used in formatted Dates.
	 */
	private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	/**
	 * The Date format used in formatted Dates.
	 */
	private static final SimpleDateFormat monthYearFormatter = new SimpleDateFormat("MMM-YY");

	/**
	 * Returns the current Date.
	 *
	 * @return the current Date
	 */
	public static Date getCurrentDate() {
		return TimeUtilities.getDateFromTime(TimeUtilities.getCurrentTime());
	}

	/**
	 * Returns the current time as UTC milliseconds from the epoch.
	 *
	 * @return long of current time
	 */
	public static long getCurrentTime() {
		return Calendar.getInstance().getTimeInMillis();
	}

	/**
	 * Returns a Date from given long of UTC milliseconds from the epoch.
	 *
	 * @param epochTime milliseconds from the epoch
	 * @return Date of given time from given UTC milliseconds from the epoch
	 */
	public static Date getDateFromTime(final long epochTime) {
		Objects.requireNonNull(epochTime);
		return new Date(epochTime);
	}

	/**
	 * Returns a Date from given Long of UTC milliseconds from the epoch.
	 *
	 * @param epochTime milliseconds from the epoch
	 * @return Date of given time from given UTC milliseconds from the epoch
	 */
	public static Date getDateFromTime(final Long epochTime) {
		Objects.requireNonNull(epochTime);
		return TimeUtilities.getDateFromTime(epochTime);
	}

	/**
	 * Returns a formatted Date String from given long of UTC milliseconds from the
	 * epoch.
	 *
	 * @param epochTime milliseconds from the epoch
	 * @return String of given date from given UTC milliseconds from the epoch
	 */
	public static String getFormattedDateFromTime(final long epochTime) {
		Objects.requireNonNull(epochTime);
		return TimeUtilities.dateFormatter.format(new Date(epochTime));
	}

	/**
	 * Returns a formatted Date Time String from given long of UTC milliseconds from
	 * the epoch.
	 *
	 * @param epochTime milliseconds from the epoch
	 * @return String of given date and time from given UTC milliseconds from the
	 *         epoch
	 */
	public static String getFormattedDateTimeFromTime(final long epochTime) {
		Objects.requireNonNull(epochTime);
		return TimeUtilities.dateTimeFormatter.format(new Date(epochTime));
	}

	/**
	 * Returns a formatted Month Year String from given long of UTC milliseconds
	 * from the epoch.
	 *
	 * @param epochTime milliseconds from the epoch
	 * @return String of given month year from given UTC milliseconds from the epoch
	 */
	public static String getFormattedMonthYearFromTime(final long epochTime) {
		Objects.requireNonNull(epochTime);
		return TimeUtilities.monthYearFormatter.format(new Date(epochTime));
	}

	/**
	 * Returns a formatted Month Year String from given long of UTC milliseconds
	 * from the epoch.
	 *
	 * @param epochTime milliseconds from the epoch
	 * @return String of given month year from given UTC milliseconds from the epoch
	 */
	public static String getFormattedMonthYearFromTime(final Long epochTime) {
		Objects.requireNonNull(epochTime);
		return TimeUtilities.getFormattedMonthYearFromTime(epochTime);
	}
}

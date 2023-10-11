/**
 * This package contains utility classes related to various aspects of the application.
 */
package com.protonmail.landrevillejf.cognos.categories.api.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The {@code CronUtil} class provides utility methods for generating cron expressions based on dates.
 */
@SuppressWarnings("CheckStyle")
public class CronUtil {
	/** Logger. */
	@SuppressWarnings("CheckStyle")
	private final static Logger logger = LoggerFactory.getLogger(CronUtil.class);

	/** Date scheduled. */
	private final Date mDate;
	private final Calendar mCal;

	private String mMins;
	private String mHours;
	private String mDaysOfMonth;
	private String mMonths;
	private String mYears;

	/**
	 * Constructor.
	 *
	 * @param pDate date scheduled
	 */
	public CronUtil(final Date pDate) {
		this.mDate = pDate;
		mCal = Calendar.getInstance();
		this.generateCronExpression();
	}

	/**
	 * Generate the cron expression components based on the provided date.
	 */
	private void generateCronExpression() {
		mCal.setTime(mDate);

		this.mHours = String.valueOf(mCal.get(Calendar.HOUR_OF_DAY));
		this.mMins = String.valueOf(mCal.get(Calendar.MINUTE));
		this.mDaysOfMonth = String.valueOf(mCal.get(Calendar.DAY_OF_MONTH));
		this.mMonths = new SimpleDateFormat("MM").format(mCal.getTime());
		this.mYears = String.valueOf(mCal.get(Calendar.YEAR));
	}

	/**
	 * Get the date that this utility is based on.
	 *
	 * @return mDate
	 */
	public Date getDate() {
		return mDate;
	}

	/**
	 * Get the seconds component of the cron expression.
	 *
	 * @return "0" (constant for seconds in cron)
	 */
	public String getSeconds() {
		return "0";
	}

	/**
	 * Get the minutes component of the cron expression.
	 *
	 * @return mMins
	 */
	public String getMins() {
		return mMins;
	}

	/**
	 * Get the days of the week component of the cron expression.
	 *
	 * @return "?" (used for days of the week in cron)
	 */
	public String getDaysOfWeek() {
		return "?";
	}

	/**
	 * Get the hours component of the cron expression.
	 *
	 * @return mHours
	 */
	public String getHours() {
		return mHours;
	}

	/**
	 * Get the days of the month component of the cron expression.
	 *
	 * @return mDaysOfMonth
	 */
	public String getDaysOfMonth() {
		return mDaysOfMonth;
	}

	/**
	 * Get the months component of the cron expression.
	 *
	 * @return mMonths
	 */
	public String getMonths() {
		return mMonths;
	}

	/**
	 * Get the years component of the cron expression.
	 *
	 * @return mYears
	 */
	public String getYears() {
		return mYears;
	}

	/**
	 * Create a cron expression based on the provided date.
	 *
	 * @param date date scheduled
	 * @throws Exception Failed to create a cron expression
	 */
	public static void createCronExpression(final Date date) throws Exception {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dt = dateFormat.format(date);
			final Date cronDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt);
			CronUtil calHelper = new CronUtil(cronDate);
			String cron = calHelper.getSeconds() + " " + calHelper.getMins() + " " + calHelper.getHours() + " "
					+ calHelper.getDaysOfMonth() + " " + calHelper.getMonths() + " " + calHelper.getDaysOfWeek() + " "
					+ calHelper.getYears();
			logger.info(cron);
		} catch (Exception e) {
			// Log the exception
			logger.error("Failed to createCronExpression {}", e.getMessage(), e);
			throw new Exception("Failed to createCronExpression " + e.getMessage());
		}
	}

	/**
	 * Main method for testing cron expression generation.
	 *
	 * @param args params
	 * @throws Exception Error
	 */
	public static void main(String[] args) throws Exception {
		Date date = new Date();

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Date parsedDate = dateFormat.parse("2011-10-02 18:48:05.123");
			Timestamp timestamp = new Timestamp(parsedDate.getTime());
			date = timestamp;
			logger.info(timestamp.toString());
		} catch (Exception e) {
			// Log the exception
			logger.error("Error {}", e.getMessage(), e);
		}
		CronUtil.createCronExpression(date);
	}
}

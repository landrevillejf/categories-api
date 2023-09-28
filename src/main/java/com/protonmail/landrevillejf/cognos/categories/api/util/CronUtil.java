package com.protonmail.landrevillejf.cognos.categories.api.util;

import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Author;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Maintainer;
import com.protonmail.landrevillejf.cognos.categories.api.util.annotation.documentation.Revision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Author(name = "Jean-Francois Landreville", enterprise = "Lanaforge Inc.",email = "landrevillejf@protonmail.com")
@Maintainer(name = "Jean-Francois Landreville",enterprise = "Lanaforge Inc.", email = "landrevillejf@protonmail.com")
@Revision(
		date = "2020-01-01",
		revision = 1,
		comments = "Author CronUtil"
)
@SuppressWarnings("CheckStyle")
public class CronUtil {
	/**Logger.
	 *
	 */
	@SuppressWarnings("CheckStyle")
	private final static Logger logger = LoggerFactory.getLogger(CronUtil.class);
	/**
	 *
	 */
	private final Date mDate;
	/**
	 *
	 */
	private final Calendar mCal;

	/**
	 *
	 */
	private String mMins;
	/**
	 *
	 */
	private String mHours;
	/**
	 *
	 */
	private String mDaysOfMonth;
	/**
	 *
	 */
	private String mMonths;

	/**
	 *
	 */
	private String mYears;

	/**Constructor.
	 *
	 * @param pDate date scheduled
	 */
	public CronUtil(final Date pDate) {
		this.mDate = pDate;
		mCal = Calendar.getInstance();
		this.generateCronExpression();
	}

	/**generateCronExpression.
	 *
	 */
	private void generateCronExpression() {
		mCal.setTime(mDate);

        this.mHours = String.valueOf(mCal.get(Calendar.HOUR_OF_DAY));

        this.mMins = String.valueOf(mCal.get(Calendar.MINUTE));

        this.mDaysOfMonth = String.valueOf(mCal.get(Calendar.DAY_OF_MONTH));

        this.mMonths = new SimpleDateFormat("MM").format(mCal.getTime());

        this.mYears = String.valueOf(mCal.get(Calendar.YEAR));

	}

	/**getDate.
	 *
	 * @return mDate
	 */
	public Date getDate() {
		return mDate;
	}

	/**getSeconds.
	 *
	 * @return mSeconds
	 */
	public String getSeconds() {
        return "0";
	}

	/**getMins.
	 *
	 * @return mMins
	 */
	public String getMins() {
		return mMins;
	}

	/**getDaysOfWeek.
	 *
	 * @return mDaysOfWeek
	 */
	public String getDaysOfWeek() {
        return "?";
	}

	/**getHours.
	 *
	 * @return mHours
	 */
	public String getHours() {
		return mHours;
	}

	/**getDaysOfMonth.
	 *
	 * @return mDaysOfMonth
	 */
	public String getDaysOfMonth() {
		return mDaysOfMonth;
	}

	/**getMonths.
	 *
	 * @return mMonths
	 */
	public String getMonths() {
		return mMonths;
	}

	/**getYears.
	 *
	 * @return mYears
	 */
	public String getYears() {
		return mYears;
	}

	/**createCronExpression.
	 *
	 * @param date date scheduled
	 * @throws Exception Failed to createCronExpression
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

	/**Main.
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
		} catch (Exception e) { // this generic but you can control another types of exception
			// look the origin of excption
			logger.error("Error {}", e.getMessage(), e);
		}
		CronUtil.createCronExpression(date);
	}
}

package by.epam.jmp.app.tradesystem.core.util;

import java.sql.Date;
import java.util.Calendar;

/**
 * Util class for working with dates
 */
public final class DateUtil {

    /**
     * Util class cannot be instantiated
     */
    private DateUtil() { }

    /**
     * Get today date
     *
     * @return today java.sql.Date
     */
    public static Date getTodaySqlDate() {
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        return new Date(timeInMillis);
    }

}
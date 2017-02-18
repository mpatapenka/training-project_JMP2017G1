package by.epam.jmp.app.tradesystem.util;

import java.sql.Date;
import java.util.Calendar;

public final class DateUtil {

    private DateUtil() { }

    public static Date getTodaySqlDate() {
        Calendar calendar = Calendar.getInstance();
        long timeInMillis = calendar.getTimeInMillis();
        return new Date(timeInMillis);
    }

}

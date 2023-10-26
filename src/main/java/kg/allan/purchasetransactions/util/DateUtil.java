package kg.allan.purchasetransactions.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author allan
 */
public class DateUtil {
    public static final DateTimeFormatter FORMATTER_ISO8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String iso8601Of(LocalDate date){
        return date.getYear() + "-" + String.format("%02d", date.getMonthValue()) + "-" + String.format("%02d", date.getDayOfMonth());
    }
}

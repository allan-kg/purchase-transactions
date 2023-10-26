/*
 * DateUtil
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Allan Krama Guimarães
 */
public class DateUtil {
    public static final DateTimeFormatter FORMATTER_ISO8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String iso8601Of(LocalDate date){
        return date.getYear() + "-" + String.format("%02d", date.getMonthValue()) + "-" + String.format("%02d", date.getDayOfMonth());
    }
}

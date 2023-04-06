package util.dateTime;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public interface DateTimeValidator {
    LocalDate convertToLocalDate(Date date);
    LocalTime convertToLocalTime(Time time);
    public Date convertToDate(LocalDate dateToConvert);
    public Time convertToTime(LocalTime timeToConvert);
    public LocalDate convertStrToLocalDate(String dateStr);
    public LocalTime convertStrToLocalTime(String timeStr);
}

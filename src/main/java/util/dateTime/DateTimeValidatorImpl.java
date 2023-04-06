package util.dateTime;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateTimeValidatorImpl implements DateTimeValidator {
    private static final String datePattern = "d.M.yyyy";
    private static final String timePattern = "H:m";
    private final static  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePattern);
    private final static  DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timePattern);

    public LocalDate convertToLocalDate(Date sqlDate){ //обработать ошибки
        return Optional.ofNullable(sqlDate)
                  .map(Date::toLocalDate)
                  .orElse(null);

    }

    public LocalTime convertToLocalTime(Time sqlTime){//обработать ошибки
        return sqlTime.toLocalTime();
    }

    @Override
    public LocalDate convertStrToLocalDate(String dateStr) {
        LocalDate ld;
        try {
            ld = LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
        return ld;
    }

    @Override
    public LocalTime convertStrToLocalTime(String timeStr) {
        LocalTime lt;
        try {
            lt = LocalTime.parse(timeStr, timeFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
        return lt;
    }

    public Date convertToDate(LocalDate dateToConvert) {
        return Date.valueOf(dateToConvert);

    }

    public Time convertToTime(LocalTime timeToConvert){
        return Time.valueOf(timeToConvert);
    }

}

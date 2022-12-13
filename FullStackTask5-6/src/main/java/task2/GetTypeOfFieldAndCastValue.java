package task2;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GetTypeOfFieldAndCastValue {
    public static Object cast(Field field, String value, String formatDate) throws ParseException {
        Object castedObject = null;

        if (field.getType().equals(String.class)) {
            castedObject = value;
        } else if (field.getType().equals(Integer.class)) {
            castedObject = Integer.parseInt(value);
        } else if (field.getType().equals(int.class)) {
            castedObject = Integer.parseInt(value);
        } else if (field.getType().equals(Instant.class)) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate);
            try {
                LocalDateTime dateTime = LocalDateTime.parse(value, formatter);
                castedObject = dateTime.toInstant(ZoneOffset.UTC);
            } catch (DateTimeException e) {
                throw new DateTimeParseException(String.format("Illegal Date and Time format, value cannot be parsed in format: %s ", formatDate), formatDate, 1);
            }
        }
        return castedObject;
    }


}

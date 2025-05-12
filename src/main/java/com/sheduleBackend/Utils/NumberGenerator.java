package com.sheduleBackend.Utils;

import java.time.LocalDateTime;

public class NumberGenerator {
    public static long generateNumber() {
        LocalDateTime timestamp = getCurrentTimestamp();

        // Extract timestamp components
        int year = timestamp.getYear() % 100; // Get last two digits of the year
        int month = timestamp.getMonthValue();
        int day = timestamp.getDayOfMonth();
        int hour = timestamp.getHour();
        int minute = timestamp.getMinute();
        int second = timestamp.getSecond();

        // Concatenate timestamp components
        String studentNumberString = String.format("%02d%02d%02d%02d%02d%02d", year, month, day, hour, minute, second);

        // Convert to long
        return Long.parseLong(studentNumberString);
    }

    private static LocalDateTime getCurrentTimestamp() {
        return LocalDateTime.now();
    }
}

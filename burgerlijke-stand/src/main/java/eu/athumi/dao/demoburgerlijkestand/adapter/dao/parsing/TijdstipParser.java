package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.TijdstipJSON;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TijdstipParser {

    public static String parseTijdstip(TijdstipJSON tijdstip) {
        if (Objects.isNull(tijdstip)) {
            return "/";
        } else if (!Objects.isNull(tijdstip.beschrijvingTijdstip()) && !tijdstip.beschrijvingTijdstip().isBlank()) {
            return tijdstip.beschrijvingTijdstip();
        }
        return parseLocalDateTime(tijdstip.datum());
    }

    public static String parseLocalDateTime(LocalDateTime date) {
        if (Objects.isNull(date)) {
            return "/";
        }
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'om' HH:mm"));
    }

    public static String parseLocalDate(LocalDate date) {
        if (Objects.isNull(date)) {
            return "-";
        }
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    public static String parseDateString(String date) {
        if (Objects.isNull(date) || date.isEmpty()) {
            return "-";
        }
        return formatDateString(date);
    }

    public static String parseDateString(String date, LocalTime uur) {
        if (Objects.isNull(date) || date.isEmpty()) {
            return "-";
        }
        return formatDateTimeString(date, uur);
    }


    public static String formatDateString(String dateString) {
        if (Objects.isNull(dateString) || dateString.isEmpty()) {
            return "";
        }

        if (dateString.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            return dateString;
        }

        Matcher matcher = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2}).*$").matcher(dateString);
        if (matcher.matches()) {
            String year = matcher.group(1);
            String month = matcher.group(2);
            String day = matcher.group(3);
            return String.format("%s/%s/%s", day, month, year);
        }

        return "";
    }


    public static String formatDateTimeString(String date, LocalTime uur) {
        if (Objects.isNull(uur)) {
            return formatDateString(date);
        }
        return "%s om %s".formatted(formatDateString(date), parseLocalTime(uur));
    }

    public static String parseLocalTime(LocalTime time) {
        if (Objects.isNull(time)) {
            return "-";
        }
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}

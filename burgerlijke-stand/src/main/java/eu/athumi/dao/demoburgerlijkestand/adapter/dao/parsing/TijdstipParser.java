package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.TijdstipJSON;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TijdstipParser {

    public static String parseTijdstip(TijdstipJSON tijdstip) {
        if (Objects.isNull(tijdstip)) {
            return "/";
        } else if(!Objects.isNull(tijdstip.beschrijvingTijdstip())) {
            return tijdstip.beschrijvingTijdstip();
        }
        return parseLocalDateTime(tijdstip.datum()) ;
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

    public static String parseLocalTime(LocalTime time) {
        if (Objects.isNull(time)) {
            return "-";
        }
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public static String parseLocalDateTime(LocalDate datum, LocalTime uur) {
        if (Objects.isNull(datum)) {
            return "-";
        }
        if(Objects.isNull(uur)) {
            return parseLocalDate(datum);
        }
        return parseLocalDateTime(LocalDateTime.of(datum, uur));
    }
}

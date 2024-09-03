package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.TijdstipJSON;

import java.time.LocalDateTime;
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
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                + " om " + date.format(DateTimeFormatter.ofPattern("hh:mm")) ;
    }

}

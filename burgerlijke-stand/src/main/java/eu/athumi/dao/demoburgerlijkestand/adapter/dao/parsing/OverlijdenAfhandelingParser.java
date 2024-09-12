package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden.OverlijdenAfhandelingOutputJSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class OverlijdenAfhandelingParser {

    public static String parseOverlijdenAfhandeling(OverlijdenAfhandelingOutputJSON overlijdenAfhandeling) {
        return Optional.ofNullable(overlijdenAfhandeling.datum()).map(OverlijdenAfhandelingParser::asString).orElse(null);
    }

    private static String asString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("'Opgeladen op' dd/MM/yyyy 'om' HH'u'mm"));
    }
}

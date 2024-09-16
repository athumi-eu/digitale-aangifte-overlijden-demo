package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden.OverlijdenAfhandelingOutputJSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class OverlijdenAfhandelingParser {

    public static String parseOverlijdenAfhandeling(OverlijdenAfhandelingOutputJSON overlijdenAfhandeling) {
        String datum = Optional.ofNullable(overlijdenAfhandeling.datum())
                .map(OverlijdenAfhandelingParser::datumAsString)
                .orElse(null);
        String oplaadDatum = Optional.ofNullable(overlijdenAfhandeling.oplaadDatum())
                .map(OverlijdenAfhandelingParser::oplaadDatumAsString)
                .orElse(null);
        return datum == null ? oplaadDatum : datum.concat(" | ").concat(oplaadDatum == null ? "" : oplaadDatum);
    }

    private static String oplaadDatumAsString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("'Opgeladen op' dd/MM/yyyy 'om' HH'u'mm"));
    }

    private static String datumAsString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("'Aangemaakt op' dd/MM/yyyy 'om' HH'u'mm"));
    }
}

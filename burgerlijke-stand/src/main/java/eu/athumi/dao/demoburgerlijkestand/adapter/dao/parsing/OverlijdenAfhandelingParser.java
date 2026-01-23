package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden.OverlijdenAfhandelingOutputJSON;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class OverlijdenAfhandelingParser {

    public static String parseOverlijdenAfhandeling(OverlijdenAfhandelingOutputJSON overlijdenAfhandeling) {
        StringBuilder sb = new StringBuilder();

        Optional.ofNullable(overlijdenAfhandeling.aktenummer())
                .map(OverlijdenAfhandelingParser::aktenummerAsString)
                .ifPresent(item -> sb.append(item).append(" | "));

        Optional.ofNullable(overlijdenAfhandeling.oplaadDatum())
                .map(OverlijdenAfhandelingParser::oplaadDatumAsString)
                .ifPresent(item -> sb.append(item).append(" | "));

        Optional.ofNullable(overlijdenAfhandeling.datum())
                .map(OverlijdenAfhandelingParser::datumAsString)
                .ifPresent(item -> sb.append(item).append(" | "));

        Optional.ofNullable(overlijdenAfhandeling.gearchiveerdOp())
                .map(OverlijdenAfhandelingParser::geArchiveerdOpAsString)
                .ifPresent(sb::append);
        
        return sb.toString();
    }

    private static String aktenummerAsString(String aktenummer) {
        return "Aktenummer %s".formatted(aktenummer);
    }

    private static String oplaadDatumAsString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("'Opgeladen op' dd/MM/yyyy 'om' HH:mm"));
    }

    private static String datumAsString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("'Aangemaakt op' dd/MM/yyyy 'om' HH:mm"));
    }

    private static String geArchiveerdOpAsString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("'Gearchiveerd op' dd/MM/yyyy 'om' HH:mm"));
    }
}

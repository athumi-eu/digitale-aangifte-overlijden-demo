package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.overlijden;

import java.time.LocalDateTime;

public record OverlijdenAfhandelingOutputJSON(
        OverlijdenAfhandelingOutputType type,
        LocalDateTime datum,
        LocalDateTime oplaadDatum,
        String documentatie
) {
}

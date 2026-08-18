package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.toestemming;

import java.time.LocalDateTime;

public record ToestemmingJSON(
        ToestemmingStatus status,
        String link,
        LocalDateTime opgeladenOp,
        LocalDateTime aangemaaktOp,
        LocalDateTime verwijderdOp,
        ToestemmingVerwijderdDoor verwijderdDoor,
        LocalDateTime afleverenMogelijkVanaf,
        boolean nieuweToestemmingAangeraden
) {
}

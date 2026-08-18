package eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.toestemming.ToestemmingJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.toestemming.ToestemmingStatus;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.toestemming.ToestemmingVerwijderdDoor;

import java.util.Objects;

public record ToestemmingParser(ToestemmingJSON toestemming) {

    public boolean isAanwezig() {
        return !Objects.isNull(toestemming) && !Objects.isNull(status()) && status() != ToestemmingStatus.GEEN_AANWEZIG;
    }

    public ToestemmingStatus status() {
        return Objects.isNull(toestemming) ? null : toestemming.status();
    }

    public String link() {
        return Objects.isNull(toestemming) ? null : toestemming.link();
    }

    public String parsedOpgeladenOp() {
        return Objects.isNull(toestemming) ? "/" : TijdstipParser.parseLocalDateTime(toestemming.opgeladenOp());
    }

    public String parsedAangemaaktOp() {
        return Objects.isNull(toestemming) ? "/" : TijdstipParser.parseLocalDateTime(toestemming.aangemaaktOp());
    }

    public String parsedVerwijderdOp() {
        return Objects.isNull(toestemming) ? "/" : TijdstipParser.parseLocalDateTime(toestemming.verwijderdOp());
    }

    public ToestemmingVerwijderdDoor verwijderdDoor() {
        return Objects.isNull(toestemming) ? null : toestemming.verwijderdDoor();
    }

    public String parsedAfleverenMogelijkVanaf() {
        return Objects.isNull(toestemming) ? "/" : TijdstipParser.parseLocalDateTime(toestemming.afleverenMogelijkVanaf());
    }

    public boolean nieuweToestemmingAangeraden() {
        return !Objects.isNull(toestemming) && toestemming.nieuweToestemmingAangeraden();
    }
}

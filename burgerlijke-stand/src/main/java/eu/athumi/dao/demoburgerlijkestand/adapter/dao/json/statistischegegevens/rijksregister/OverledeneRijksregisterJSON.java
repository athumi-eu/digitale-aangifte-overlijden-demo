package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregister;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.AdresJSON;

import java.time.LocalDate;

public record OverledeneRijksregisterJSON(
        Geslacht geslacht,
        LocalDate geboortedatum,
        AdresJSON verblijfsAdres
) {
}

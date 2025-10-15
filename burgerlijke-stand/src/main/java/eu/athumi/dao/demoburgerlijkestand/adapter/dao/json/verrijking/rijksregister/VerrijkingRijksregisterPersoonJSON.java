package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking.rijksregister;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;

import java.time.LocalDate;

public record VerrijkingRijksregisterPersoonJSON(
        String rijksregisternummer,
        String naam,
        String voornaam,
        Geslacht geslacht,
        String geboortedatum
) {
}

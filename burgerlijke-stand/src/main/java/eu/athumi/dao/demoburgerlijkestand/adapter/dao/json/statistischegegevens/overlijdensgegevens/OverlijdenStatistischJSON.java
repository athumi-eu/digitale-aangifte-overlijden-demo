package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteEnLandJSON;

import java.time.LocalDateTime;

public record OverlijdenStatistischJSON(
    LocalDateTime tijdstip,
    String beschrijvingTijdstip,
    PlaatsOverlijdenTypeJSON plaats,
    String plaatsBeschrijving,
    GemeenteEnLandJSON adres,
    boolean doodGeboren
) {}

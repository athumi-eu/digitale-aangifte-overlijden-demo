package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens;

import java.time.LocalDateTime;

public record OverlijdenStatistischJSON(
    LocalDateTime tijdstip,
    String beschrijvingTijdstip,
    PlaatsOverlijdenTypeJSON plaats,
    String plaatsBeschrijving,
    AdresStatistischJSON adres,
    boolean doodGeboren
) {}

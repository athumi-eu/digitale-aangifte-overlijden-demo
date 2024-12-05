package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.Plaats;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.PlaatsTypeJSON;

import java.time.LocalDateTime;
import java.time.LocalTime;

public record OverlijdenStatistischJSON(
    LocalDateTime tijdstip,
    String beschrijvingTijdstip,
    PlaatsTypeJSON plaats,
    String plaatsBeschrijving,
    AdresStatistischJSON adres
) implements Plaats {}

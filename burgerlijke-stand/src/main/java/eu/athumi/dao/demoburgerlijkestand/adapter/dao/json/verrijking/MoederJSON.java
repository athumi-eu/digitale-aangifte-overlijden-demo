package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.verrijking;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;

import java.time.LocalDate;

public record MoederJSON(
    String rijksregisternummer,
    String naam,
    String voornaam,
    Geslacht geslacht,
    LocalDate geboorteDatum,
    boolean nietVerblijfshouder,
    boolean nietGekend
) {}

package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;


import java.time.LocalDate;

public record AsWettelijkePartnerJSON(
    AsWettelijkePartnerType type,
    String rijksregisternummer,
    String voornaam,
    String naam,
    LocalDate datumOverlijden,
    String plaatsOverlijden,
    LocalDate geboorteDatum
){}

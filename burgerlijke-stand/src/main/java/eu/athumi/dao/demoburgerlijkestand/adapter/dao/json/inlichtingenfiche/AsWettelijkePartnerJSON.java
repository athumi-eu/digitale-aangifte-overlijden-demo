package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;

import java.time.LocalDate;

public record AsWettelijkePartnerJSON(
    AsWettelijkePartnerType type,
    String rijksregisternummer,
    String voornaam,
    String naam,
    LocalDate datumOverlijden,
    String plaatsOverlijden,
    String geboorteDatum
) {
    public String getGeboorteDatum() {
        return TijdstipParser.formatDateString(geboorteDatum);
    }
}

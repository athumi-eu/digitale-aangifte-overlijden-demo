package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.parsing.TijdstipParser;

import java.util.Optional;

public record VaderOfMeemoederJSON(
        String rijksregisternummer,
        String voornaam,
        String naam,
        Geslacht geslacht,
        String geboortelocatie,
        String geboorteDatum
) {
    public String parsedGeboorteDatum() {
        return Optional.ofNullable(geboorteDatum).map(TijdstipParser::formatDateString).orElse("");
    }
}

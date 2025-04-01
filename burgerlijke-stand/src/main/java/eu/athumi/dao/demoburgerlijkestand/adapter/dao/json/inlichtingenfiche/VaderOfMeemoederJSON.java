package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public record VaderOfMeemoederJSON(
        String rijksregisternummer,
        String voornaam,
        String naam,
        Geslacht geslacht,
        String geboortelocatie,
        LocalDate geboorteDatum
) {
    public String parsedGeboorteDatum() {
        return Optional.ofNullable(geboorteDatum).map(d -> d.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).orElse("");
    }
}

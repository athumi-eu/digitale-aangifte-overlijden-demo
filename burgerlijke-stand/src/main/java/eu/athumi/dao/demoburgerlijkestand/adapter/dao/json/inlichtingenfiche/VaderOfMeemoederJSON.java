package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record VaderOfMeemoederJSON(
        String rijksregisternummer,
        String voornaam,
        String naam,
        Geslacht geslacht,
        String geboortelocatie,
        LocalDate geboorteDatum
) {
    public String parsedGeboorteDatum() {
        return geboorteDatum.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}

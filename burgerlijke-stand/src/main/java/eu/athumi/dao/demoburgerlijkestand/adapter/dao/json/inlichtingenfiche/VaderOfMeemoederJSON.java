package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;

public record VaderOfMeemoederJSON(
        String rijksregisternummer,
        String voornaam,
        String naam,
        Geslacht geslacht,
        String geboortelocatie,
        String geboorteDatum
) {
}

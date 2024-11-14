package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import java.util.List;

public record LocatieJSON(
    LocatieType locatieType,
    String locatie,
    NabestaandeJSON nabestaande
) {}

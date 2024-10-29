package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import java.time.LocalDate;

public record InlichtingenficheJSON(
    LocalDate aanmaakDatum,
    BegraafplaatsJSON begraafplaats,
    CrematieJSON crematie,
    InformatieAkteLevenloosKindJSON informatieAkteLevenloosKind
) {}

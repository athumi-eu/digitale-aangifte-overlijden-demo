package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import java.time.LocalDateTime;
import java.util.List;

public record InlichtingenficheJSON(
    LocalDateTime aanmaakDatum,
    LocalDateTime wijzigingDatum,
    VerwerkingstermijnJSON verwerkingstermijn,
    List<DocumentJSON> documenten,
    BegraafplaatsJSON begraafplaats,
    CrematieJSON crematie,
    AsWettelijkePartnerJSON asWettelijkePartner,
    InformatieAkteLevenloosKindJSON informatieAkteLevenloosKind
) {


}

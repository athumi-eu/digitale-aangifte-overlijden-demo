package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

import java.util.UUID;

public record CrematieJSON(
    UUID crematoriumInBelgie,
    String anderCrematorium,
    BestemmingAsJSON bestemmingAs,
    AsWettelijkePartnerJSON asWettelijkePartner
) {}

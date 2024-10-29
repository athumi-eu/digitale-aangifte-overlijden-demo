package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.inlichtingenfiche;

public record CrematieJSON(
    String crematoriumInBelgie,
    String anderCrematorium,
    BestemmingAsJSON bestemmingAs,
    AsWettelijkePartnerJSON asWettelijkePartner
) {}

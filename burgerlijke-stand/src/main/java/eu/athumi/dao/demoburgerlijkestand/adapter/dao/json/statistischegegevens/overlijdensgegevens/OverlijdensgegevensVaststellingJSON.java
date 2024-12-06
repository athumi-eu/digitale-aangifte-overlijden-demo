package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens;

public record OverlijdensgegevensVaststellingJSON(
    OverlijdenStatistischJSON overlijden,
    eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.MeervoudigeZwangerschapJSON meervoudigeZwangerschap
) {}

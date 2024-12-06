package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens;

public record OverlijdensgegevensDepartementZorgJSON(
    OverlijdenStatistischJSON overlijden,
    eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.MeervoudigeZwangerschapJSON meervoudigeZwangerschap
) {}

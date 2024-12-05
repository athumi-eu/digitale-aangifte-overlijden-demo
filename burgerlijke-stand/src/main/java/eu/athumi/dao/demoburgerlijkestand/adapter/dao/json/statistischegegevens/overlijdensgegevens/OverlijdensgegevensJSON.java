package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.MeervoudigeZwangerschapJSON;

public record OverlijdensgegevensJSON(
    OverlijdenStatistischJSON overlijden,
    MeervoudigeZwangerschapJSON meervoudigeZwangerschap
) {}

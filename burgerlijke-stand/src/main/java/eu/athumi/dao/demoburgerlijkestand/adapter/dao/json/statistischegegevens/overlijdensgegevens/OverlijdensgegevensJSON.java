package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens;

public record OverlijdensgegevensJSON(
    OverlijdensgegevensVaststellingJSON overlijdensgegevensVaststelling,
    OverlijdensgegevensBurgerlijkeStandJSON overlijdensgegevensBurgerlijkeStand,
    OverlijdensgegevensDepartementZorgJSON overlijdensgegevensDepartementZorg
) {}

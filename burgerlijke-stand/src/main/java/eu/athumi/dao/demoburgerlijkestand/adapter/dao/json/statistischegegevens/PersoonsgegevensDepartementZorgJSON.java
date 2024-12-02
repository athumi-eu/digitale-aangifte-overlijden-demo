package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.ouder.OudersJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.OverledeneJSON;

public record PersoonsgegevensDepartementZorgJSON(OverledeneJSON overledene, OudersJSON ouders) {

}

package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens;


import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregister.PersoonsgegevensRijksregisterJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling.PersoonsgegevensVaststellingJSON;

public record PersoonsgegevensJSON(PersoonsgegevensDepartementZorgJSON departementZorg,
                                   PersoonsgegevensVaststellingJSON vaststelling,
                                   PersoonsgegevensRijksregisterJSON rijksregister) {

}

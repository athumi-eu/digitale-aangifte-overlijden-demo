package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.geboorte.GeboorteJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteEnLandJSON;

public record MoederVaststellingJSON(Geslacht geslacht, GemeenteEnLandJSON verblijfplaats, GeboorteJSON geboorte) {
}
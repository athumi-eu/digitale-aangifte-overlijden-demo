package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.vaststelling;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens.AdresStatistischJSON;

public record MoederVaststellingJSON(Geslacht geslacht, AdresStatistischJSON verblijfplaats,
                                     GemeenteEnLand geboorteAdres) {}
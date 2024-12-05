package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overlijdensgegevens.OverlijdensgegevensJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregisternummers.RijksregisternummersJSON;

public record StatistischeGegevensJSON(PersoonsgegevensJSON persoonsgegevens, OverlijdensgegevensJSON overlijdensgegevens, RijksregisternummersJSON rijksregisternummers) {

}

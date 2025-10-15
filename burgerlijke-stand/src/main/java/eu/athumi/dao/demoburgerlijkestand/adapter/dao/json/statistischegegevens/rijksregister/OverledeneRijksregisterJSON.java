package eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.rijksregister;

import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.Geslacht;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.plaats.GemeenteEnLand;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.NationaliteitJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.locatie.GemeenteEnLandJSON;
import eu.athumi.dao.demoburgerlijkestand.adapter.dao.json.statistischegegevens.overledene.BurgerlijkeStaatJSON;

import java.util.List;

public record OverledeneRijksregisterJSON(
        Geslacht geslacht,
        String geboortedatum,
        GemeenteEnLand geboorteAdres,
        GemeenteEnLandJSON verblijfsAdres,
        NationaliteitJSON nationaliteit,
        List<BurgerlijkeStaatJSON> burgerlijkeStaten
) {
}
